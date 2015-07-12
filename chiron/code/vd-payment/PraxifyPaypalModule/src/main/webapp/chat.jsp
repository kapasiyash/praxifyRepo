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






<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
        <link rel="stylesheet" href="css/custom.css"/>
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
        <title>Events - Home</title>

    </head>
    <body>
        <div class="container">
            <header>
                <div class="row">
                    <div class="row-fluid">

                        <div class="site-title span6">
                            <h2>Virtual Doctor</h2>
                        </div>
                                           
                                            
                           <%
                           if(user!=null){ 
                        	   %>
 <div class="pull-right user-detail">
                                <strong>Welcome, <%=user.getFirstName() +" "+ user.getLastName() %></strong>
                                <a class="btn btn-danger btn-mini" href="logout.jsp"><i class="fa fa-sign-out"></i> LOGOUT</a>
                            </div>
                                          
	                        	   
                        	   <%
                           }
                           %>                
                       </div>                      
                                         
                </div>
            </header>

            <div class="row">
                <nav class="navbar navbar-default" role="navigation">

                    <div class="navbar-inner">
                        <ul class="nav navbar-nav">
                        
                        	<%
                        		if(user==null){
                        			%>
                        				<li class="active"><a href="index.jsp">Home</a></li>
                            <li><a href="login.jsp">Login</a></li>
                            <li><a href="register.jsp">Register</a></li>
                            <li><a href="feeds.jsp">Feeds</a></li> 
                        			<%
                        		} else {
                        			
                        			%>
                        				<li class="active"><a href="index.jsp">Home</a></li>
                            <li><a href="chat.jsp">chat</a></li>
                            <li><a href="register.jsp">abcd</a></li>
                            <li><a href="feeds.jsp">Feeds</a></li> 
                        			<%
                        			
                        		}
                        	%>
                        
                                                       
                            
                        </ul>
                    </div>
                </nav>
</div>            
            <div class="row">
                <form class="form-inline" method="post" action="search.jsp">

                    <div class="row-fluid">

                        <input type="text" class="input-medium span9" placeholder="Event title" name="title" />

                        <input type="submit" class="btn btn-success span2 btn-wide-fix" value="Search" />

                    </div>
                </form>
            </div>
            
                        <div class="row content">
                
                <h3>
                
                Start VD Chat Session</h3>
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
<%if(request.getParameter("message")!=null) {
	%>
	<%=request.getParameter("message")  %>
	<%
} %>

</font>
</h1>         



</body>
</html>