<%-- 
    Document   : titleSearch
    Created on : 10-Dec-2017, 19:44:10
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div name="titleSearch">
    <form action="FrontController" method="post">
        <input type="radio" name="action" value="novelByName"> Search By Name<br>
        <input type="radio" name="action" value="novelByAuthor"> Search By Author<br>
        <input type="radio" name="action" value="novelByID"> Novel By ID <br>  
        <input type="text" name="titleRequest"><br>
        <input type="submit" value="Search">
    </form>
</div>
