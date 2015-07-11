<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="com.chiron.vd.entity.User"%>
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
                
                <h3>Latest Events</h3>
              
                