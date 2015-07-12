package com.chiron.vd.paypal.api.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import urn.ebay.api.PayPalAPI.CreateRecurringPaymentsProfileReq;
import urn.ebay.api.PayPalAPI.CreateRecurringPaymentsProfileRequestType;
import urn.ebay.api.PayPalAPI.CreateRecurringPaymentsProfileResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.BillingAgreementDetailsType;
import urn.ebay.apis.eBLBaseComponents.BillingCodeType;
import urn.ebay.apis.eBLBaseComponents.BillingPeriodDetailsType;
import urn.ebay.apis.eBLBaseComponents.BillingPeriodType;
import urn.ebay.apis.eBLBaseComponents.CreateRecurringPaymentsProfileRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.CreditCardDetailsType;
import urn.ebay.apis.eBLBaseComponents.CreditCardTypeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.RecurringPaymentsProfileDetailsType;
import urn.ebay.apis.eBLBaseComponents.ScheduleDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

/**
 * Servlet implementation class RecurringPaypalPaymentServlet
 */
@WebServlet(name="recurringpayment",urlPatterns={})
public class RecurringPaypalPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecurringPaypalPaymentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processPayment(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processPayment(request, response);
	}

	
	private void processPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
	
		String paymentMode = request.getParameter("paymentMode");
		
		if("cc".equals(paymentMode)) {
			prepareCreditCard(request);
			
		} else if("paypal".equals(paymentMode)) {
			preparePaypal(request);
		}
	}
	
	
	private void preparePaypal(HttpServletRequest request) {
		
		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		paymentDetails.setPaymentAction(PaymentActionCodeType.fromValue("Sale"));

		BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.setCurrencyID(CurrencyCodeType.fromValue("USD"));
		orderTotal.setValue("1");
		paymentDetails.setOrderTotal(orderTotal);
		List<PaymentDetailsType> paymentDetailsList = new ArrayList<PaymentDetailsType>();
		paymentDetailsList.add(paymentDetails);

		SetExpressCheckoutRequestDetailsType setExpressCheckoutRequestDetails = new SetExpressCheckoutRequestDetailsType();
		setExpressCheckoutRequestDetails.setReturnURL("https://devtools-paypal.com/guide/recurring_payment_ec?success=true");
		setExpressCheckoutRequestDetails.setCancelURL("https://devtools-paypal.com/guide/recurring_payment_ec?cancel=true");

		setExpressCheckoutRequestDetails.setPaymentDetails(paymentDetailsList);
		  
		BillingAgreementDetailsType billingAgreement = new BillingAgreementDetailsType(BillingCodeType.fromValue("RecurringPayments"));
		billingAgreement.setBillingAgreementDescription("recurring billing for Virtual Doctor subscription");
		List<BillingAgreementDetailsType> billList = new ArrayList<BillingAgreementDetailsType>();
		billList.add(billingAgreement);
		setExpressCheckoutRequestDetails.setBillingAgreementDetails(billList);

		SetExpressCheckoutRequestType setExpressCheckoutRequest = new SetExpressCheckoutRequestType(setExpressCheckoutRequestDetails);
		setExpressCheckoutRequest.setVersion("104.0");

		SetExpressCheckoutReq setExpressCheckoutReq = new SetExpressCheckoutReq();
		setExpressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutRequest);

		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		sdkConfig.put("acct1.UserName", "jb-us-seller_api1.paypal.com");
		sdkConfig.put("acct1.Password", "WX4WTU3S8MY44S7F");
		sdkConfig.put("acct1.Signature","AFcWxV21C7fd0v3bYYYRCpSSRl31A7yDhhsPUU2XhtMoZXsWHFxu-RWy");
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
		try {
			SetExpressCheckoutResponseType setExpressCheckoutResponse = service.setExpressCheckout(setExpressCheckoutReq);
			
			String url = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+setExpressCheckoutResponse.getToken();
			System.out.println(url);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	private void prepareCreditCard(HttpServletRequest request) {
		
		String creditCardcc = request.getParameter("creditCard");
		String ccv2 = request.getParameter("ccv2");
		String expiryMonthCC = request.getParameter("expiryMonthCC");
		String expiryyearcc = request.getParameter("expiryyearcc");
		String creditCardTypecc = request.getParameter("creditCardTypecc");
		
		RecurringPaymentsProfileDetailsType profileDetails = new RecurringPaymentsProfileDetailsType(dateFormat.format(new Date())+"T00:00:00:000Z");

		BasicAmountType paymentAmount = new BasicAmountType(CurrencyCodeType.USD, "1.0");
		BillingPeriodType period = BillingPeriodType.fromValue("Day");
		int frequency = 30;
		BillingPeriodDetailsType paymentPeriod = new BillingPeriodDetailsType(period, frequency, paymentAmount);

		ScheduleDetailsType scheduleDetails = new ScheduleDetailsType();
		scheduleDetails.setDescription("recurring billing for Virtual Doctor subscription");
		scheduleDetails.setPaymentPeriod(paymentPeriod);

		CreditCardDetailsType creditCard = new CreditCardDetailsType();
		creditCard.setCreditCardNumber(creditCardcc);
		creditCard.setCVV2(ccv2);
		creditCard.setExpMonth(Integer.parseInt(expiryMonthCC));
		creditCard.setExpYear(Integer.parseInt(expiryyearcc)); 
		creditCard.setCreditCardType(CreditCardTypeType.fromValue(creditCardTypecc));

		CreateRecurringPaymentsProfileRequestDetailsType createRPProfileRequestDetails = new CreateRecurringPaymentsProfileRequestDetailsType(profileDetails, scheduleDetails);
		createRPProfileRequestDetails.setCreditCard(creditCard);

		CreateRecurringPaymentsProfileRequestType createRPProfileRequest = new CreateRecurringPaymentsProfileRequestType();
		createRPProfileRequest.setCreateRecurringPaymentsProfileRequestDetails(createRPProfileRequestDetails);

		CreateRecurringPaymentsProfileReq createRPPProfileReq = new CreateRecurringPaymentsProfileReq();
		createRPPProfileReq.setCreateRecurringPaymentsProfileRequest(createRPProfileRequest);
		
		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		sdkConfig.put("acct1.UserName", "jb-us-seller_api1.paypal.com");
		sdkConfig.put("acct1.Password", "WX4WTU3S8MY44S7F");
		sdkConfig.put("acct1.Signature","AFcWxV21C7fd0v3bYYYRCpSSRl31A7yDhhsPUU2XhtMoZXsWHFxu-RWy");
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
		try {
			CreateRecurringPaymentsProfileResponseType createRPProfileResponse = service.createRecurringPaymentsProfile(createRPPProfileReq);
			
			System.out.println(createRPProfileResponse.getCreateRecurringPaymentsProfileResponseDetails().getTransactionID());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	private void successResponse() {
		
		RecurringPaymentsProfileDetailsType profileDetails = new RecurringPaymentsProfileDetailsType(dateFormat.format(new Date())+"T00:00:00:000Z");

		BasicAmountType paymentAmount = new BasicAmountType(CurrencyCodeType.USD, "1.0");
		BillingPeriodType period = BillingPeriodType.fromValue("Day");
		int frequency = 10;
		BillingPeriodDetailsType paymentPeriod = new BillingPeriodDetailsType(period, frequency, paymentAmount);

		ScheduleDetailsType scheduleDetails = new ScheduleDetailsType();
		scheduleDetails.setDescription("recurringbilling");
		scheduleDetails.setPaymentPeriod(paymentPeriod);

		CreateRecurringPaymentsProfileRequestDetailsType createRPProfileRequestDetails = new CreateRecurringPaymentsProfileRequestDetailsType(profileDetails, scheduleDetails);
		createRPProfileRequestDetails.setToken("EC-6S622886143969533");

		CreateRecurringPaymentsProfileRequestType createRPProfileRequest = new CreateRecurringPaymentsProfileRequestType();
		createRPProfileRequest.setCreateRecurringPaymentsProfileRequestDetails(createRPProfileRequestDetails);

		CreateRecurringPaymentsProfileReq createRPPProfileReq = new CreateRecurringPaymentsProfileReq();
		createRPPProfileReq.setCreateRecurringPaymentsProfileRequest(createRPProfileRequest);

		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		sdkConfig.put("acct1.UserName", "jb-us-seller_api1.paypal.com");
		sdkConfig.put("acct1.Password", "WX4WTU3S8MY44S7F");
		sdkConfig.put("acct1.Signature","AFcWxV21C7fd0v3bYYYRCpSSRl31A7yDhhsPUU2XhtMoZXsWHFxu-RWy");
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
		try {
			CreateRecurringPaymentsProfileResponseType createRPProfileResponse = service.createRecurringPaymentsProfile(createRPPProfileReq);
			
			System.out.println(createRPProfileResponse.getAck());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
