<%-- 
    Document   : login
    Created on : 09-Dec-2017, 14:38:34
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        
        <style>
        
            body{
                background-color:#37383a;
            }
            
            a{
                color:white;
            }
            
            #registerForm{
                border-style:solid;
                border-color:white;
                border-width:1px;
                color:white;
                width:500px;
                height:600px;
                margin-left:400px;
                margin-top:50px;
            }
            
            .textbox{
                width:300px;
                height:40px;
                margin-top:25px;
                margin-left:100px;
                font-size:16px;
                color:white;
                background-color:#77797c;
            }
            
            #submit{
                position:absolute;
                width:100px;
                height:40px;
                margin-top:10px;
                margin-left:200px;
            }
            
        </style>
    </head>
    <body>
        <form id="registerForm" action="FrontController" method="post">
            <input type="hidden" value="signup" name="action">
            
            <input class="textbox" id="email" placeholder="Email:" type="text" name="email" required>
            <br>
            <input class="textbox" id="password" placeholder="Password:" type="text" name="password" required>
            <br>
            <input class="textbox" id="fname" placeholder="First name:" type="text" name="firstName" required>
            <br>
            <input class="textbox" id="lname" placeholder="Last name:" type="text" name="lastName">
            <br>
            <input class="textbox" id="country" placeholder="Country:" type="text" name="country" require>
            <br>
            <input class="textbox" id="address1" placeholder="Address Line 1:" type="text" name="addressLine1" require>
            <br>
            <input class="textbox" id="address2" placeholder="Address Line 2:" type="text" name="addressLine2">
            <br><br>
            <input id="submit" type="submit" value="Register">
        </form>
    </body>
</html>
