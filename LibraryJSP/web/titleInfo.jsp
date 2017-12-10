<%-- 
    Document   : titleInfo
    Created on : 10-Dec-2017, 21:54:22
    Author     : Sean
--%>

<%@page import="Dtos.Title"%>
<%@page import="DAO.TitleDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% int titleID = Integer.parseInt(request.getParameter("titleID"));
    if (titleID != 0) { %>
<% TitleDAO titleDao = new TitleDAO("librarydatabase"); %>
<% Title selectedTitle = titleDao.searchByID(titleID);%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=selectedTitle.getNovelName()%></title>
    </head>
    <body>
        <div id="titleDetails">
            <h1><%=selectedTitle.getNovelName()%></h1>

            Author:
            <p><%= selectedTitle.getAuthor()%></p><br/>
            
            Description:
            <p><%= selectedTitle.getTitleDescription()%></p><br/>
            
            Stock:
            <p><%= selectedTitle.getStock()%></p><br/>
            
            On Loan:
            <p><%= selectedTitle.getOnLoan()%></p>
        </div>
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
