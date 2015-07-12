
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
         
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/custom.css"/>
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/include.css"/>
        <title>VD - Sign In</title>

    </head>
    <body>
        <div class="container">
            <header>
                <div class="row">
                    <div class="row-fluid">

                        <div class="site-title span6">
                            <h2>Virtual Doctor Manager</h2>
                        </div>
                        
                    </div>
                </div>
            </header>

            <div class="row">
                <nav class="navbar navbar-default" role="navigation">

                    <div class="navbar-inner">
                        <ul class="nav navbar-nav">
                            <li><a href="index.jsp">Home</a></li><li><a href="login.jsp">Login</a></li><li class="active"><a href="register.jsp">Register</a></li><li><a href="feeds.jsp">Feeds</a></li>                            
                            
                        </ul>
                    </div>
                </nav>
</div>            
            <div class="row content">
                <form class="login-form"  method="post" action="signup">
                    <h3 class="form-title">Sign Up to Virtual Doctor Manager</h3>
                    
                    <table>
                    	<tr>
                    		<td>
                    			<input type="text" class="input-medium span3" placeholder="Username" name="username" value=""/>
                    		</td>
                    		<td>
                    			<input type="password" class="input-medium span3" placeholder="Password" name="password"/>
                    		</td>
                    	</tr>
                    	<tr>
                    		<td>
                    			<input type="text" class="input-medium span3" placeholder="Firstname" name="fname" value=""/>
                    		</td>
                    		<td>
                    			<input type="text" class="input-medium span3" placeholder="Lastname" name="lname" value=""/>
                    		</td>
                    	</tr>
                    	<tr>
                    		<td>
                    			<input type="text" class="input-medium span3" placeholder="Email" name="emailid" value=""/>
                    		</td>
                    		<td>
                    			<input type="text" class="input-medium span3" placeholder="Phone" name="phone" value=""/>
                    		</td>
                    	</tr>
                    	<tr>
                    		<td>
                    			<input type="text" class="input-medium span3" placeholder="Age" name="age" value=""/>
                    		</td>
                    		<td>
                    			<input type="text" class="input-medium span3" placeholder="Address" name="address" value=""/>
                    		</td>
                    	</tr>
                    	
                    </table>
                    
                   
                    <input type="submit" class="btn btn-success" value="Sign Up" />


                </form>
            </div>
            
            <footer>
                <div class="row">
                    Copyright &copy; 2014
                </div>
            </footer>

        </div>
    </body>
</html>