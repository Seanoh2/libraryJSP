<%-- 
    Document   : header
    Created on : 08-Dec-2017, 19:27:02
    Author     : Sean
--%>
<head>
    <style>
        #header{
            width:100%;
            color:white;
            height:50px;
            border-style:solid;
            border-color:white;
            border-width:1px;
            font-size:15px;
        }
        
        .link{
            -moz-transition:0.5s;
            color:white;
            margin-left:20px;
        }
        
        .link:hover{
            -moz-transition:0.5s;
            color:red;
            margin-left:20px;
        }
        
        a{
            -moz-transition:0.5s;
            color:white;
            margin-left:20px;
        }
        
        a:hover{
            -moz-transition:0.5s;
            color:red;
        }
        
        #message{
            position:absolute;
            margin-left:800px;
            font-size:30px;
        }
    </style>
</head>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="header">
    <%if(session.getAttribute("message") != null) {
        String message = (String) session.getAttribute("message");
        out.print("<h1 id='message'>" + message + "</h1>");
        session.removeAttribute("message");
    }%>
    
    <ul>
        <% if (session.getAttribute("user") != null) { %>
        <a class="link" href="myProfile.jsp">My Profile</a>
        <a class="link"  href="viewTitles.jsp">View Titles</a>
        <a class="link"  href="titleSearch.jsp">Search for a Title</a>
        <a class="link"  href="viewLoans.jsp">View Loans</a>
        <a class="link"  href="FrontController?action=logout">Logout</a>
        <%} else { %>
        <a class="link"  href="login.jsp">Login</a>
        <a class="link"  href="register.jsp">Register</a>
        <% } %>
    </ul>
</div>
