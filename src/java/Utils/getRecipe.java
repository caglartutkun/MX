/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entity.*;
import AdapterServices.*;
import java.util.*;
import com.google.gson.*;
import java.sql.*;

/**
 *
 * @author Caglar
 */
public class getRecipe {

    private execSQL ex = new execSQL();
    private ResultSet rs;
    private allRecipe ar = new allRecipe();

    public List<Head> getFilteredHead( String title,Categories category, String yield) {
        List<Head> HeadList = new ArrayList<>();
        rs = ex.getHeadFiltered(title, category, yield);
        try {
            while (rs.next()) {
                Head head = new Head();
                head.setId(rs.getInt("id"));
                head.setTitle(rs.getString("title"));
                head.setYield(rs.getString("yield"));
                Categories categories = new Categories();
                categories.setCat(Arrays.asList(rs.getString("categories").split(",")));
                head.setCategories(categories);
                HeadList.add(head);
            }
            return HeadList;
        } catch (SQLException e) {
            return HeadList;
        }
    }

    public Head getHeadbyID(int RecipeID) {
        Head head = new Head();
        rs = ex.getHeadbyID(RecipeID);
        try {
            while (rs.next()) {
                head.setId(rs.getInt("id"));
                head.setTitle(rs.getString("title"));
                head.setYield(rs.getString("yield"));
                Categories categories = new Categories();
                categories.setCat(Arrays.asList(rs.getString("categories").split(",")));
                head.setCategories(categories);
            }
            return head;
        } catch (SQLException e) {
            return head;
        }
    }

    public All getRecipe(int RecipeID) {
        All all = new All();
        List<Recipe> RecipeList = new ArrayList<>();
        List<Head> HeadList = Arrays.asList(getHeadbyID(RecipeID));
        for (int i = 0; i < HeadList.size(); i++) {
            Head head = new Head();
            head.setId(HeadList.get(i).getId());
            head.setCategories(HeadList.get(i).getCategories());
            head.setTitle(HeadList.get(i).getTitle());
            head.setYield(HeadList.get(i).getYield());
            Recipe recipe = new Recipe();
            recipe.setHead(head);
            recipe.setDirections(ar.getDirectionsClassDB(HeadList.get(i).getId()));
            recipe.setIngredients(ar.getIngredientsClassDB(HeadList.get(i).getId()));
            RecipeList.add(recipe);
        }
        all.setResults(HeadList.size());
        all.setRecipes(RecipeList);
        all.setTotal(HeadList.size());
        return all;
    }

    public String getRecipeJson(int RecipeID) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            All a = getRecipe(RecipeID);
            return gson.toJson(a, All.class);
        } catch (Exception e) {
            return e.toString();
        }
    }
   
}
