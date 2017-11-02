<%-- 
    Document   : recipe
    Created on : Nov 2, 2017, 1:51:37 PM
    Author     : Caglar
--%>

<%@page import="Utils.getRecipe"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Mendix Recipe Service!</h1>
        <%
            String RecipeID=request.getParameter("RecipeID");
    if (RecipeID == null) {
        out.println("Sorry Mate!");
    } else {
        getRecipe a = new getRecipe();
        out.print(a.getRecipeHTML(Integer.valueOf(RecipeID)));
    }
%>
        
        
    </body>
</html>
