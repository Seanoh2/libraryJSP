<%-- 
    Document   : viewTitles
    Created on : 10-Dec-2017, 18:51:11
    Author     : Sean
--%>

<%@page import="Dtos.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dtos.Title"%>
<%@page import="DAO.TitleDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%TitleDAO titleDao = new TitleDAO("librarydatabase");%>
<% ArrayList<Title> results;
    if (session.getAttribute("results") == null) {
        results = titleDao.viewAllTitles();
        session.setAttribute("message", "No titles found.");
    } else {
        results = (ArrayList<Title>) session.getAttribute("results");
    }%>
<% User currentUser = (User) session.getAttribute("user");
   %>
<html>
    <head>
        <%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Titles</title>
        
        <style>
            body{
                background-color:#37383a;
            }
            
            #viewTitles{
                border-style:solid;
                border-width:1px;
                border-color:white;
                width:300px;
                margin-left:500px;
                margin-top:200px;
                font-size:16px;
            }
        </style>
    </head>
    <body>
        <div id="viewTitles" name="viewTitles">
            <center>
                <% for (Title t : results) {%>   
            <a href="titleInfo.jsp?titleID=<%=t.getTitleID()%>"><%=t.getNovelName()%></a>

            <% if (currentUser != null && currentUser.getIsAdmin() == 1) {%>
            <button type="button" onclick="location.href = 'deleteTitle.jsp?titleID=<%=t.getTitleID()%>';">Delete</button> 
            <% } %>
            <br> 
            <% }
                session.removeAttribute("results");
            %>
            </center>
        </div>
    </body>
</html>
