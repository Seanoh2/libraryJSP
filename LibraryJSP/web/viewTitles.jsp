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
<%ArrayList<Title> titles = titleDao.viewAllTitles();%>
<% User currentUser = (User) session.getAttribute("user"); %>
<html>
    <body>     
<div name="viewTitles">
    <% for (Title t : titles) {%>   
    <a href="titleInfo.jsp?titleID=<%=t.getTitleID()%>"><%=t.getNovelName()%></a>
        <%if (currentUser != null) { %>
        <button type="button" onclick="location.href = 'borrowTitle.jsp?titleID=<%t.getTitleID();%>';">Borrow</button>
            <% if (currentUser.getIsAdmin() == 1) { %>
                <button type="button" onclick="location.href = 'deleteTitle.jsp?titleID=<%t.getTitleID();%>';">Delete</button> 
            <% } %>
        <% } %>
    <br> 
    <% }%>
</div>
    </body>
</html>
