<%-- 
    Document   : congrats
    Created on : Mar 30, 2019, 6:03:05 PM
    Author     : Brian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Congratulations</title>
        <style>
            body
            {
                background: url('congratulations.png') no-repeat 50% 50% fixed;
                background-size: cover;
            }
        </style>
    </head>
    <body onload ="alert('You got a 50$ amazon gift card for choosing the right egg.')">
        <audio autoplay loop>
            <source src="congrats.mp3" type="audio/mpeg">
        </audio>
    </body>
</html>
