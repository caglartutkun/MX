/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


import AdapterServices.execSQL;
import java.sql.ResultSet;
import Entity.*;
import AdapterServices.*;
import java.util.*;
import com.google.gson.*;
import java.sql.*;
/**
 *
 * @author Caglar
 */
public class getCategories {
    private execSQL ex = new execSQL();
    private ResultSet rs;
    private CategoriesJson categories = new CategoriesJson();
    
    
    public String getAllCategoriesJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            categories = getCategories();
            return gson.toJson(categories, CategoriesJson.class);
        } catch (Exception e) {
            return e.toString();
        }
    }

    private CategoriesJson getCategories() {
        try{
            rs=ex.getCategories();
            List<String> cat = new ArrayList<String>();
            int i=0;
            while(rs.next()){
                cat.add(rs.getString("title"));
                i++;
            }
            categories.setCat(cat); 
            categories.setResults(i);
        } catch (Exception e){
            
        }
        return categories;
    }
            
}
