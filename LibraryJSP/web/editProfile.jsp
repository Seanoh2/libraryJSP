<%-- 
    Document   : editProfile
    Created on : 12-Dec-2017, 13:54:17
    Author     : Sean
--%>

<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% User currentUser = (User) session.getAttribute("user"); %>
<% if(currentUser != null) { %>
<html>
    <head>
        <%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
    </head>
    <body>
        <form action="FrontController" method="post">
            <input type="hidden" name="action" value="edituser">
            <input type="text" name="email" value="<%=currentUser.getEmail() %>"><br/>
            <input type="text" name="firstName" value="<%=currentUser.getFirstName() %>"><br/>
            <input type="text" name="lastName" value="<%=currentUser.getLastName() %>"><br/>
            <input type="text" name="country" value="<%=currentUser.getCountry() %>"><br/>
            <input type="text" name="addressLine1" value="<%=currentUser.getAddressLine1()%>"><br/>
            <input type="text" name="addressLine2" value="<%=currentUser.getAddressLine2()%>">
            <input type="submit" value="submit">
        </form>
    </body>
</html>

<% } else {
 String errorMessage = "No ID was found.";
    session.setAttribute("errorMessage", errorMessage);
%>
<script type="text/javascript">
    window.location = "error.jsp";
</script>

<% } %>