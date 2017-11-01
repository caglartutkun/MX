<%-- 
    Document   : index
    Created on : Oct 31, 2017, 3:18:25 PM
    Author     : Caglar
--%>
<%@page import="Utils.allRecipe"%>
<%@page import="Utils.addRecipe" %>
<%@page import="Utils.getRecipe" %>
<%@page import="Utils.filterRecipe" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Mendix!</h1>
        <jsp:useBean id="index" class="Utils.addRecipe" />
        <%
            //addRecipe a = new addRecipe();
            //out.print("Result: "+a.setAdd("Nothing!"));

            //allRecipe b = new allRecipe();
            //out.print("Result: "+b.getAllJson());
            getRecipe c = new getRecipe();
            out.print("Result: " + c.getRecipeJson(27));

        %>
    </body>

</html>
