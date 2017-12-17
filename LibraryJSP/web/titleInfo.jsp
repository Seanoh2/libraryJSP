<%-- 
    Document   : titleInfo
    Created on : 10-Dec-2017, 21:54:22
    Author     : Sean
--%>

<%@page import="DAO.ratingDAO"%>
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
        <%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=selectedTitle.getNovelName()%></title>
        
        <style>
            
            body{
                background-color:#37383a;
                color:white;
                font-size:12px;
            }
            
            
        
            #titleDetails{
                position:absolute;
                width:500px;
                height:500px;
                margin-left:10px;
            }
            
            #borrowLink{
                font-size:16px;
                
            }
            
            #rating{
                position:absolute;
                width:300px;
                height:100px;
                border-style:solid;
                border-width:1px;
                border-color: white;
                margin-left:200px;
                margin-top:400px;
            }
            
        </style>
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
            
            <% if(session.getAttribute("user") != null) { %>
            <a id="borrowLink" href="FrontController?action=borrowTitle&titleID=<%=selectedTitle.getTitleID()%>">Borrow Title</a>
            <% } %>
        </div>
        
        <div id="rating">
            <%
                ratingDAO ratingdao = new ratingDAO("librarydatabase");
                int id = (Integer)session.getAttribute("id");
                double average = ratingdao.getAverageRatingByID(id);
                out.println("Average rating: " + average);
            %>
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
