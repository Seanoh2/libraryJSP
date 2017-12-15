<%-- 
    Document   : myProfile
    Created on : 11-Dec-2017, 17:20:20
    Author     : Sean
--%>


<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<% User currentUser = (User) session.getAttribute("user"); %>
<% if(currentUser != null) { %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Profile</title>
        
        <style>
        
            body{
                background-color:#37383a;
                color:white;
            }
            
        </style>
    </head>
    <body>
        <div id="profileDetails">
            <h1>User: <%=currentUser.getEmail()%></h1>
            <p>First name: <%=currentUser.getFirstName() %></p><br/>
            <p>Last name: <%=currentUser.getLastName() %></p><br/>
            <p>Country: <%=currentUser.getCountry() %></p><br/>
            <p>Address Line 1: <%=currentUser.getAddressLine1() %></p><br/>
            <p>Address Line 2: <%=currentUser.getAddressLine2() %></p><br/>
            <p>User id: <%=currentUser.getUserID() %></p><br/>
            
            <%if(currentUser.getIsAdmin() == 1) { %>
            <p>User is an admin.</p>
            <% } else { %>
            <p>User is not an admin.</p>
            <% } %>
            
        </div>      
        <a href="editProfile.jsp">Edit Profile</a>
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
