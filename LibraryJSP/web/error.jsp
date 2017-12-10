<%-- 
    Document   : error
    Created on : 21-Nov-2017, 16:36:02
    Author     : Seanoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    function goBack() {
        window.history.back();
    }
</script> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1><%=session.getAttribute("errorMessage")%></h1>
        <%session.removeAttribute("errorMessage");%>


        <button onclick="goBack()">Go Back</button>

    </body>
</html>
