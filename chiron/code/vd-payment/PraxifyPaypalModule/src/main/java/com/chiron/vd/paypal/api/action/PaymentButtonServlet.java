package com.chiron.vd.paypal.api.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;

import com.chiron.vd.entity.Transaction;
import com.chiron.vd.entity.User;
import com.chiron.vd.utility.HibernateUtil;

/**
 * Servlet implementation class PaymentButtonServlet
 */
public class PaymentButtonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentButtonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if("cancel".equals(action)) {
			
			cancelPayment(request,response);
			
		} else if("complete".equals(action)) {
			
			completePayment(request,response);
		}
		
		
		
	}

	private void completePayment(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String host = request.getHeader("Host");
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			HttpSession sessionHttp = request.getSession();
			
			User user = null;

			if(sessionHttp.getAttribute("userBean")!=null) {
				user = (User) sessionHttp.getAttribute("userBean");
			}
			
			Transaction transaction = new Transaction();
			transaction.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			transaction.setStatus("DONE");
			transaction.setTransactionId(sessionHttp.getId());
			transaction.setEmailId(user.getEmailAddress());
			
			session.save(transaction);
			
			session.getTransaction().commit();
			
			response.sendRedirect("http://"+host+"/PraxifyPaypalModule/startchat.jsp");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

	private void cancelPayment(HttpServletRequest request,
			HttpServletResponse response) {
		
		System.out.println("inside cancel payment");
	
		String host = request.getHeader("Host");
		
		try {
			
			String msg = "Gand marava cancel kryu Loda";
			
			response.sendRedirect("http://"+host+"/PraxifyPaypalModule/chat.jsp?message="+msg);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
