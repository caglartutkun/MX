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
public class allRecipe {

    private ResultSet rs = null;

    public List<Head> getHeadClassDB() {
        try {
            List<Head> hl = new ArrayList<Head>();
            execSQL e = new execSQL();
            rs = e.getHeadAll();
            while (rs.next()) {
                Head h = new Head();
                List<String> cat = new ArrayList<String>();
                h.setId(rs.getInt("id"));
                h.setTitle(rs.getString("title"));
                h.setYield(rs.getString("yield"));
                String[] str = rs.getString("categories").split(",");
                cat.addAll(Arrays.asList(str));
                Categories c = new Categories();
                c.setCat(cat);
                h.setCategories(c);
                hl.add(h);
            }
            return hl;

        } catch (Exception e) {
            return null;
        }
    }

    public Directions getDirectionsClassDB(int RecipeID) {
        Directions d = new Directions();
        try {
            execSQL e = new execSQL();
            rs = e.getDirections(RecipeID);
            while (rs.next()) {
                d.setStep(rs.getString("Direction"));
            }
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public Ingredients getIngredientsClassDB(int RecipeID){
        Ingredients ing = new Ingredients();
        try {
            execSQL e = new execSQL();
            rs = e.getIngredients(RecipeID);
            while (rs.next()) {
                IngDiv ingd = new IngDiv();
            }
            return ing;
        } catch (Exception e) {
            return null;
        }
    }
 
    public String getHeadJson() {
        List<Head> h = getHeadClassDB();
        Head hh = new Head();
        String retval = "";
        Gson gson = new Gson();

        try {
            for (int i = 0; i < h.size(); i++) {
                retval += gson.toJson(h.get(i)) + "\n";
            }
        } catch (Exception e) {
            retval += "Err:" + e.toString();
        }
        return retval;
    }

    public All getAll() {
        All a = new All();
        List<Head> hl = getHeadClassDB();
        List<Recipe> r = new ArrayList<Recipe>();
        for (int i = 0; i < hl.size(); i++) {
            Recipe rec = new Recipe();
            rec.setHead(hl.get(i));
            rec.setDirections(getDirectionsClassDB(hl.get(i).getId()));
            rec.setIngredients(getIngredientsClassDB(hl.get(i).getId()));
            r.add(rec);
            a.setResults(i);
        }
        a.setRecipes(r);
        a.setTotal(hl.size());
        return a;
    }

}
