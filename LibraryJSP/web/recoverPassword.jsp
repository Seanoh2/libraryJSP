<%-- 
    Document   : recoverPassword
    Created on : 15-Dec-2017, 00:58:53
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
                color:white;
            }
            
            #recoveryForm{
                border-style:solid;
                border-color:white;
                border-width:1px;
                font-size:16px;
                width:400px;
                height:200px;   
                margin-top:200px;
            }
            
            #textbox{
                background-color:#77797c;
                color:white;
                width:300px;
                height:40px;
                font-size:16px;
                margin-top:40px;
            }
            
            #submit{
                width:100px;
                height:40px;
                background-color:#77797c;
                color:white;
            }
            
        </style>
    </head>
    <body>
    <center>
        <form id="recoveryForm" action="FrontController" method="post">
        <input type="hidden" value="passwordrecovery" name="action">
        <input id="textbox" placeholder="Enter email address associated" type="text" name="email" required>
        <br><br>
        <input id="submit" type="submit" value="Recover">
        </form>
    </center>
    </body>
</html>
