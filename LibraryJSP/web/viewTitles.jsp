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
    } else {
        results = (ArrayList<Title>) session.getAttribute("results");
    }%>
<% User currentUser = (User) session.getAttribute("user"); %>
        <div name="viewTitles">
            <% for (Title t : results) {%>   
            <a href="titleInfo.jsp?titleID=<%=t.getTitleID()%>"><%=t.getNovelName()%></a>

            <% if (currentUser != null && currentUser.getIsAdmin() == 1) { %>
            <button type="button" onclick="location.href = 'deleteTitle.jsp?titleID=<%=t.getTitleID()%>';">Delete</button> 
            <% } %>
            <br> 
            <% }
                session.removeAttribute("results");
            %>
        </div>
