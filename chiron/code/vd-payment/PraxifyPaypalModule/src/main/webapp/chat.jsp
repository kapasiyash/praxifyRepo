<%@page import="com.chiron.vd.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
User user = null;

if(session.getAttribute("userBean")!=null) {
	user = (User) session.getAttribute("userBean");
}

%>

Start VD Chat Session
<br/>

<form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
<input type="hidden" name="cmd" value="_s-xclick">
<input type="hidden" name="hosted_button_id" value="PSLQ27X6S6QGL">
<input type="hidden" name="custom" value="<%=user.getEmailAddress()%>">
<input type="image" src="https://www.paypalobjects.com/en_GB/i/btn/btn_paynowCC_LG.gif" border="0" name="submit" alt="PayPal – The safer, easier way to pay online.">
<img alt="" border="0" src="https://www.paypalobjects.com/en_GB/i/scr/pixel.gif" width="1" height="1">
</form>

<br/>

<br/>

<h1>
<font color="red">
<%=request.getParameter("message")  %>
</font>
</h1>





</body>
</html>