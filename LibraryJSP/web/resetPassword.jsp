<%-- 
    Document   : resetPassword
    Created on : 15-Dec-2017, 17:03:23
    Author     : Sean
--%>

<%@page import="Dtos.User"%>
<%@page import="DAO.EmailDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String check = request.getParameter("ID");
   EmailDAO emailDao = new EmailDAO("librarydatabase");
   User requestedUser = emailDao.checkUUID(check);
   
    if(requestedUser != null) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <form id="resetpassword" action="FrontController" method="post">
        <input type="hidden" value="resetpassword" name="action">
        <input type="hidden" value="<%=requestedUser.getUserID()%>" name="user">
        <input type="hidden" value="<%=check%>" name="UUID">        
        <input type="text" name="password" required>
        <br><br>
        <input id="submit" type="submit" value="Reset">
        </form>
    </body>
</html>

<% } else {
    session.setAttribute("errorMessage", "Error with UUID");
    response.sendRedirect("error.jsp");
} %>
