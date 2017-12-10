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
    </head>
    <body>
        <form action="FrontController" method="post">
        <input type="hidden" value="login" name="action">
        Email:
        <input type="text" name="email" required>
        <br>
        Password:
        <input type="text" name="password" required>
        <br><br>
        <input type="submit" value="Login">
        </form>
    </body>
</html>
