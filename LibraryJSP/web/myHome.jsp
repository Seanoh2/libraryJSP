<%-- 
    Document   : myHome
    Created on : 09-Dec-2017, 15:55:05
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Home</title>
    </head>
    <body>
        <%@ include file="viewTitles.jsp" %>
        <% if (session.getAttribute("user") != null) { %>
        <h1>Logged in!</h1>
        <% }%>
    </body>
</html>
