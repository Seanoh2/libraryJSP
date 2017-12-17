<%-- 
    Document   : error
    Created on : 21-Nov-2017, 16:36:02
    Author     : Seanoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
    
    body{
        background-color:#37383a;
    }

    #error{
        color:white;
        font-size:50px;
        margin-left:400px;
        margin-top:200px;
    }
    
    #submit{
        margin-left:400px;
    }
    
</style>


<script>
    function goBack() {
        window.history.back();
    }
</script> 
<html>
    <head>
        <%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <div id="Error">
        <h1 id="error"><%=session.getAttribute("errorMessage")%></h1>
        <%session.removeAttribute("errorMessage");%>


        <button id="submit" onclick="goBack()">Go Back</button>
        </div>
    </body>
</html>
