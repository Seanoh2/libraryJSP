<%-- 
    Document   : viewLoans
    Created on : 12-Dec-2017, 00:48:20
    Author     : Sean
--%>
<%@page import="Dtos.Borrowed"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dtos.User"%>
<%@page import="DAO.BorrowedDAO"%>
<% BorrowedDAO borrowedDao = new BorrowedDAO("librarydatabase");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% User currentUser = (User) session.getAttribute("user"); %>
<!DOCTYPE html>

<% if (currentUser != null) { %>
<% ArrayList<Borrowed> borrowedTitles = borrowedDao.getBorrowedByUserID(currentUser.getUserID()); //Searching the DB for relevant Loans
    ArrayList<Borrowed> currentlyOnLoan = new ArrayList(); //Used to split Loans into diffrent lists
    ArrayList<Borrowed> returnedTitles = new ArrayList(); //Used to split Loans into diffrent lists

    //Loop to split borrowed into 2
    for (Borrowed b : borrowedTitles) {
        if (b.getStatus() == 0) {
            currentlyOnLoan.add(b);
        } else {
            returnedTitles.add(b);
        }
    }
    borrowedTitles = null; //Clearing object to save on resources.
%>
<html>
    <head>
        <%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrowed Titles</title>
        
        <style>
        
            body{
                background-color:#37383a;
                color:white;
            }
            
        </style>
        
    </head>
    <body>
        <div id="onLoanDisplay">
            <table>
                <tr>
                    <th>Title</th> 
                    <th>Description</th> 
                    <th>Borrowed Date</th> 
                    <th>Return</th> 
                </tr>  
                <% for (Borrowed b : currentlyOnLoan) {%>
                <tr>
                    <td><%=b.getTitle().getNovelName()%></td>
                    <td><%=b.getTitle().getTitleDescription()%></td>
                    <td><%=b.getDaysBorrowed()%></td>
                <td><a href="checkReturn.jsp?borrowedID=<%=b.getBorrowedID()%>">Return</a></td>
                
                </tr>
                
                <% } %>
            </table>
        </div>
        <div id="returnedTitles">
            <table>
                <tr>
                    <th>Title</th> 
                    <th>Description</th> 
                    <th>Borrowed Date</th> 
                </tr>  
                <% for (Borrowed b : returnedTitles) {%>
                <tr>
                    <td><%=b.getTitle().getNovelName()%></td>
                    <td><%=b.getTitle().getTitleDescription()%></td>
                    <td><%=b.getDaysBorrowed()%></td>
                </tr>
                
                <% } %>
            </table>
        </div>
    </body>
</html>

<% } else {
    String errorMessage = "No user logged in.";
    session.setAttribute("errorMessage", errorMessage);
%>
<script type="text/javascript">
    window.location = "error.jsp";
</script>

<% }%>