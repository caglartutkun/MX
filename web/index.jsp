<%-- 
    Document   : index
    Created on : Oct 31, 2017, 3:18:25 PM
    Author     : Caglar
--%>
<%@page import="Entity.Head"%>
<%@page import="Entity.CategoriesJson"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Categories"%>
<%@page import="Utils.getCategories"%>
<%@page import="Utils.allRecipe"%>
<%@page import="Utils.addRecipe" %>
<%@page import="Utils.getRecipe" %>
<%@page import="Utils.filterRecipe" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table style="width:100%;text-align: left">

            <tr> 
                <td colspan="2"><h1>Recipe App!</h1></td>
            </tr>
            <td style="border-width:1px; border-color:Black ; border-style :groove ; width: 20%;vertical-align: top">
                <b>Categories</b><br>
                <jsp:useBean id="categories" class="Utils.getCategories" />
                <%
                    CategoriesJson js = new CategoriesJson();
                    js = categories.getCategories();
                    List<String> cccc = new ArrayList<String>();
                    cccc = js.getCat();
                    String str = "<ul>";
                    for (int i = 0; i < cccc.size(); i++) {
                        //    str+="<input type='checkbox' name='"+cccc.get(i)+"' value='ON' /><br>";
                        //str+="<input type='submit' value="+cccc.get(i)+" name="+cccc.get(i)+" /><br>";
                        str += "<li><a href='index.jsp?category="+cccc.get(i)+"'>" + cccc.get(i) + "</a></li>";
                    }
                    str += "</ul>";
                    out.print(str);
                %>
            </td>

            <td style="border-width:1px; border-color:Black ; border-style :groove ; width: 80%;vertical-align: top">

                <jsp:useBean id="filter" class="Utils.filterRecipe" />
                <%

                    Categories cat = new Categories();
                    List<String> catList = new ArrayList<String>();
                    String category = request.getParameter("category");
                    if (category != null) {
                        catList.add(category);
                    }
                    cat.setCat(catList);
                    List<Head> hh = new ArrayList<Head>();
                    hh = filter.getFilteredHead(null, cat, null);
                    String head = "<table style='width:100%;text-align: left'><tr><td><b>Title</b></td><td><b>Categories</b></td><td><b>Yield</b></td></tr>";
                    for (int i = 0; i < hh.size(); i++) {
                        head += "<tr><td>";
                        head += "<a href='recipe.jsp?RecipeID=" + hh.get(i).getId() + "' target='_blank'>" + hh.get(i).getTitle() + "</a>";
                        //head += "<a href='recipe.jsp' target='_blank'></a>";
                        head += "</td><td>";
                        catList = hh.get(i).getCategories().getCat();
                        String tmp = "", sep = "";
                        for (int j = 0; j < catList.size(); j++) {
                            tmp += sep + catList.get(j);
                            sep = ",";
                        }
                        head += tmp + "</td><td>";
                        head += hh.get(i).getYield();
                        head += "</td>";
                        head += "</tr>";
                    }
                    head += "</table>";
                    out.print(head);
                %>

            </td>
        </tr></table>
</body>

</html>
