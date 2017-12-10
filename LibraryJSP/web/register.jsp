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
    </head>
    <body>
        <form action="FrontController" method="post">
            <input type="hidden" value="signup" name="action">
            Email:
            <input type="text" name="email" required>
            <br>
            Password:
            <input type="text" name="password" required>
            <br>
            First Name:
            <input type="text" name="firstName" required>
            <br>
            Last Name:
            <input type="text" name="lastName">
            <br>
            Country:
            <input type="text" name="country" require>
            <br>
            Address Line 1:
            <input type="text" name="addressLine1" require>
            <br>
            Address Line 2:
            <input type="text" name="addressLine2">
            <br><br>
            <input type="submit" value="Register">
        </form>
    </body>
</html>
