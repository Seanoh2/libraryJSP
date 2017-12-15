<%-- 
    Document   : login
    Created on : 09-Dec-2017, 15:45:17
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        
        <style>
        
            body{
                background-color:#37383a;
            }
            
            a{
                color:white;
            }
            
            #loginForm{
                margin-top:180px;
                margin-left: 400px;
                border-style:solid;
                border-color:white;
                border-width:1px;
                width:500px;
                height:250px;
            }

            .textbox{
                width:300px;
                height:40px;
                position:absolute;
                margin-left: 90px;
                background-color:#77797c;
                color:white;

            }

            #email{
                -moz-transition:0.3s;
                font-size:15px;
                margin-top:50px;
            }

            #password{
                -moz-transition:0.3s;
                margin-top:100px;
                font-size:15px;
            }

            #legend{
                position:absolute;
                margin-left:220px;
            }

            #submit{
                position:absolute;
                width:100px;
                height:40px;
                margin-top:120px;
                margin-left:180px;
            }

            #email:focus{
                border-color:#38fff5;
            }

            #password:focus{
                border-color:#38fff5
            }
            
            #recoverLink{
                position:absolute;
                margin-left:300px;
                margin-top:160px;
                
            }

            
        </style>
    </head>
    <body>
        <form id="loginForm" action="FrontController" method="post">
        <input type="hidden" value="login" name="action">
        <input class="textbox" id="email" type="text" name="email" placeholder="Email:" required>
        <br>
        <input class="textbox" id="password" type="password" name="password" placeholder="Password:" required>
        <br>
        <a id="recoverLink" href="recoverPassword.jsp">Recover password</a>
        <br><br>
        <input id="submit" type="submit" value="Login">
        </form>
    </body>
</html>
