<%-- 
    Document   : titleSearch
    Created on : 10-Dec-2017, 19:44:10
    Author     : Sean
--%>
<style>
    body{
        background-color:#37383a;
        color:white;
    }
    
    #titleSearch{
        position:absolute;
        margin-left:450px;
        margin-top:100px;
        border-style:solid;
        border-color:white;
        border-width:1px;
        width:420px;
        height:200px;
    }
    
    #search{
        position:absolute;
        margin-top:-40px;
        margin-left:150px;
        width:250px;
        height:40px;
        font-size:16px;
        background-color:#77797c;
        color:white;
    }
    
    #submit{
        position:absolute;
        margin-top:10px;
        margin-left:150px;
        width:100px;
        height:40px;
        
    }
</style>
<%@ include file="header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="titleSearch" name="titleSearch">
    <form action="FrontController" method="post">
        <input type="radio" name="action" value="novelByName"> Search By Name<br>
        <input type="radio" name="action" value="novelByAuthor"> Search By Author<br>
        <input type="radio" name="action" value="novelByID"> Novel By ID <br>  
        <input id="search" type="text" name="titleRequest"><br>
        <input id="submit" type="submit" value="Search">
    </form>
</div>
