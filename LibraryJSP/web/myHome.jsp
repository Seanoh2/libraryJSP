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
        
        <style>
            body{
                background-color:#37383a;
            }
            
            #greeting{
                color:white;
                font-size:30px;
            }
        </style>
    </head>
    <body>
        <% if (session.getAttribute("user") != null) { %>
        <h1 id="greeting">Logged in</h1>
        <% }%>
    </body>
</html>
