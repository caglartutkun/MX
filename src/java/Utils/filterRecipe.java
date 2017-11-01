/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import AdapterServices.execSQL;
import Entity.All;
import Entity.Categories;
import Entity.Head;
import Entity.HeadJson;
import Entity.Recipe;
import Utils.getRecipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Caglar
 */
public class filterRecipe {

    private execSQL ex = new execSQL();
    private ResultSet rs;
    private getRecipe a = new getRecipe();
    private allRecipe ar = new allRecipe();

    public List<Head> getFilteredHead(String title, Categories category, String yield) {
        List<Head> HeadList = new ArrayList<Head>();
        try {
            rs = ex.getHeadFiltered(title, category, yield);
            while (rs.next()) {
                Head head = new Head();
                head.setId(rs.getInt("id"));
                head.setTitle(rs.getString("title"));
                head.setYield(rs.getString("yield"));
                String tmp = rs.getString("categories");
                Categories categories = new Categories();
                List<String> cat = new ArrayList<String>();
                if (tmp.length()>0) {
                    if(tmp.contains(",")){
                        cat=Arrays.asList(tmp.split(","));
                    } else {cat.add(tmp);}
                }   
                categories.setCat(cat);
                head.setCategories(categories);
                HeadList.add(head);
            }
            return HeadList;
        } catch (SQLException e) {
            return HeadList;
        }
    }

    public String getResults(String Json) {
        /*try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Head head = gson.fromJson(Json, Head.class);
            return getFilteredRecipeJson(head.getTitle(), head.getCategories(), head.getYield());
        } catch (Exception e) {
            return "HATA!:" + e.toString();
        }*/
        int str=0;
        try {
            str=1;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            str=2;            
            Head head = gson.fromJson(Json, Head.class);
            str=3;
            List<Head> HeadList = getFilteredHead(head.getTitle(), head.getCategories(), head.getYield());
            //return head.getTitle() + " " + head.getYield() + " " + HeadList.size();
            str=4;
            return getFilteredRecipeJson(head.getTitle(), head.getCategories(), head.getYield());
        } catch (Exception e) {
            return "getResults():"+e.toString()+" Step:"+String.valueOf(str);
        }
    }

    public String getFilteredRecipeJson(String title, Categories categories, String yield) {
        try {
            All all = new All();
            List<Recipe> RecipeList = new ArrayList<>();
            List<Head> HeadList = getFilteredHead(title, categories, yield);
            if (HeadList.size() > 0) {
                for (int i = 0; i < HeadList.size(); i++) {
                    Recipe recipe = new Recipe();
                    recipe.setHead(HeadList.get(i));
                    recipe.setDirections(ar.getDirectionsClassDB(HeadList.get(i).getId()));
                    recipe.setIngredients(ar.getIngredientsClassDB(HeadList.get(i).getId()));
                    RecipeList.add(recipe);
                }
                all.setResults(HeadList.size());
                all.setRecipes(RecipeList);
                all.setTotal(HeadList.size());
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                return gson.toJson(all, All.class);
            } else {
                return "Head is Empty! Size:" + String.valueOf(HeadList.size());
            }
        } catch (Exception e) {
            return "getFilteredRecipeJson()"+e.toString();
        }
    }
}
