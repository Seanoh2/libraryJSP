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
    </head>
    <body>
        <form action="FrontController" method="post">
        <input type="hidden" value="passwordrecovery" name="action">
        Please enter associated email:
        <input type="text" name="email" required>
        <br><br>
        <input type="submit" value="Recover">
        </form>
    </body>
</html>
