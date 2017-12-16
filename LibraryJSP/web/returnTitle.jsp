<%-- 
    Document   : returnTitle
    Created on : 16-Dec-2017, 20:00:50
    Author     : Tomwozzer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book returned</title>
        
        <style>
        
            body{
                background-color:#37383a;
                color:white;
            }  
            
            #container{
                width:400px;
                height:200px;
                margin-left:100px;
                border-style:solid;
                border-color:white;
                border-width:1px;
                margin-left:420px;
                margin-top:50px;
            }
            
            #message{
                color:white;
                font-size:20px;
            }
            
            #title{
                color:white;
                font-size:20px;
                margin-left:500px;
                margin-top:100px;
            }
            
            #submit{
                width:70px;
                height:20px;
                margin-left:100px;
            }
            
            
        </style>
    </head>
    <body>
        <h1 id="title"> Book returned successfully </h1>
        
        <div id="container">
            <p id="message"> Would you care to rate this novel?</p>
            
            <form id="formRate" action="FrontController" method="post">
                <input type="hidden" value="rate" name="action">
                <input type="radio" name="rating" value="1"> 1 Star<br>
                <input type="radio" name="rating" value="2"> 2 Star<br>
                <input type="radio" name="rating" value="3"> 3 Star<br>
                <input type="radio" name="rating" value="4"> 4 Star<br>
                <input type="radio" name="rating" value="5"> 5 Star<br>
                <input type="radio" name="rating" value="no"> Some other time
                <input id="submit" type="submit" name="submit" value="rate"/>
            </form>
        </div>
    </body>
</html>
