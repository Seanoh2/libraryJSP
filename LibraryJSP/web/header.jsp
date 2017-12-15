<%-- 
    Document   : header
    Created on : 08-Dec-2017, 19:27:02
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="header">
    <ul>
        <% if (session.getAttribute("user") != null) { %>
        <a href="myProfile.jsp">My Profile</a>
        <a href="viewTitles.jsp">View Titles</a>
        <a href="titleSearch.jsp">Search for a Title</a>
        <a href="viewLoans.jsp">View Loans</a>
        <a href="FrontController?action=logout">Logout</a>
        <%} else { %>
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
        <% } %>
    </ul>
</div>
