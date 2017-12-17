<%-- 
    Document   : checkReturn
    Created on : 17-Dec-2017, 18:04:14
    Author     : Sean
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="Dtos.Borrowed"%>
<%@page import="DAO.BorrowedDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% BorrowedDAO borrowedDao = new BorrowedDAO("librarydatabase");%>
<% Borrowed checkBorrowed = borrowedDao.getBorrowedByID(Integer.parseInt(request.getParameter("borrowedID"))); %>
<% String checkOverdue = checkBorrowed.getDaysBorrowed();
   double daysBetween = borrowedDao.checkDays(checkOverdue);
   double overdueFee = 0.0;

    if (daysBetween > 7) {
        overdueFee = (daysBetween - 7.0) * 0.35;
    }

%>
<html>
    <head>
        <%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check return</title>
        
        <style>
            body{
                background-color:#37383a;
                color:white;
            }
            </style>
    </head>
    <body>
        <h1>Overdue fee: <%=overdueFee%></h1>
        <form id="returnForm" action="FrontController" method="post">
            <input type="hidden" value="returnborrowed" name="action">
            <input type="hidden" value="<%=checkBorrowed.getBorrowedID()%>" name="borrowed">
            <% if (overdueFee != 0) { %>
                This is where we would pay for overdue, but we cant include as it would require
                connection to a real bank account or card, which is not something I can do.
            <% } %>
            <input id="submit" type="submit" value="Return">
        </form>
    </body>
</html>
