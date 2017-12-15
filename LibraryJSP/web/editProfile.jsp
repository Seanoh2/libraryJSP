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
        
        <style>
            
            body{
                background-color:#37383a;
                color:white;
            }
            
            #editForm{
                border-style:solid;
                border-color:white;
                border-width:1px;
                position:absolute;
                margin-left:500px;
                margin-top:100px;
                width:400px;
                height:450px;
            }
            
            .textbox{
                width:300px;
                margin-left:50px;
                margin-top:10px;
                height:40px;
                color:white;
                background-color:#77797c;
                font-size:16px;
            }
            
            #submit{
                margin-left:150px;
                margin-top:20px;
                width:100px;
                height:40px;
                
            }
            
        </style>
    </head>
    <body>
        <form id="editForm" action="FrontController" method="post">
            <input type="hidden" name="action" value="edituser">
            <input placeholder="Email: " class="textbox"  type="text" name="email" value="<%=currentUser.getEmail() %>" required><br/>
            <input placeholder="First Name: " class="textbox"  type="text" name="firstName" value="<%=currentUser.getFirstName() %>" required><br/>
            <input placeholder="Last Name: " class="textbox"  type="text" name="lastName" value="<%=currentUser.getLastName() %>" required><br/>
            <input placeholder="Counrty: " class="textbox"  type="text" name="country" value="<%=currentUser.getCountry() %>" required><br/>
            <input placeholder="Address Line 1: " class="textbox"  type="text" name="addressLine1" value="<%=currentUser.getAddressLine1()%>" required><br/>
            <input placeholder="Address Line 2: " class="textbox"  type="text" name="addressLine2" value="<%=currentUser.getAddressLine2()%>" required>
            <input id="submit" type="submit" value="submit">
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