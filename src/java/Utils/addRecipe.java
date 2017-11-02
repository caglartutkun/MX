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
public class addRecipe {

    private String jsonAdd = "{ \"head\": { \"title\": \"Amaretto Cake\", \"categories\": { \"cat\": [ \"Liquor\", \"Cakes\", \"Cake mixes\" ] }, \"yield\": \"1\" }, \"ingredients\": { \"ing-div\": [ { \"title\": \"\", \"ing\": [ { \"amt\": { \"qty\": \"1 1\\/2\", \"unit\": \"cups\" }, \"item\": \"Toasted Almonds; chopped\" }, { \"amt\": { \"qty\": \"1\", \"unit\": \"package\" }, \"item\": \"Yellow cake mix; no pudding\" }, { \"amt\": { \"qty\": \"1\", \"unit\": \"package\" }, \"item\": \"Vanilla instant pudding\" }, { \"amt\": { \"qty\": \"4\", \"unit\": \"\" }, \"item\": \"Eggs\" }, { \"amt\": { \"qty\": \"1\\/2\", \"unit\": \"cups\" }, \"item\": \"Vegetable oil\" }, { \"amt\": { \"qty\": \"1\\/2\", \"unit\": \"cups\" }, \"item\": \"Water\" }, { \"amt\": { \"qty\": \"1\\/2\", \"unit\": \"cups\" }, \"item\": \"Amaretto\" }, { \"amt\": { \"qty\": \"1\", \"unit\": \"teaspoon\" }, \"item\": \"Almond extract\" } ] }, { \"title\": \"GLAZE\", \"ing\": [ { \"amt\": { \"qty\": \"1\\/2\", \"unit\": \"cups\" }, \"item\": \"Sugar\" }, { \"amt\": { \"qty\": \"1\\/4\", \"unit\": \"cups\" }, \"item\": \"Water\" }, { \"amt\": { \"qty\": \"2\", \"unit\": \"tablespoons\" }, \"item\": \"Butter\" }, { \"amt\": { \"qty\": \"1\\/4\", \"unit\": \"cups\" }, \"item\": \"Amaretto\" }, { \"amt\": { \"qty\": \"1\\/2\", \"unit\": \"teaspoons\" }, \"item\": \"Almond extract\" } ] } ] }, \"directions\": { \"step\": \" Sprinkle 1 cup almonds into bottom of a well-greased and floured 10\\\"\\n tube pan; set aside. Combine cake mix, pudding mix, eggs, oil, water,\\n amaretto, and almond extract in a mixing bowl; beat on low speed of\\n an\\n electric mixer til dry ingredients are moistened. Increase\\n speed to medium, and beat 4 minutes. Stir in remaining 1\\/2 cup\\n almonds. Pour batter into prepared tube pan. Bake at 325~ for 1 hour\\n or til a wooden pick inserted in center comes out clean. Cool cake in pan\\n 10-15 minutes; remove from pan, and cool completely. Combine sugar,\\n water, and butter in a small saucepan; bring to a boil. Reduce heat to\\n medium and gently boil 4-5 minutes, stirring occasionally, until sugar\\n dissolves. Remove from heat, and cool 15 minutes. Stir\\n in amaretto and almond extract. Punch holes in top of cake with a\\n wooden pick; slowly spoon glaze on top of cake, allowing glaze to absorb in\\n cake.\\n Posted to MC-Recipe Digest V1 #263\\n \\n Date: Sun, 27 Oct 1996 20:04:57 +0000\\n \\n From: Cheryl Gimenez <clgimenez@earthlink.net>\\n \\n\" } }",
            jsonAll = "{ \"results\": 1, \"total\": 90, \"recipes\": [ { \"head\": { \"title\": \"30 Minute Chili\", \"categories\": { \"cat\": [ \"Main dish\", \"Chili\" ] }, \"yield\": \"6\" }, \"ingredients\": { \"ing\": [ { \"amt\": { \"qty\": \"1\", \"unit\": \"pound\" }, \"item\": \"Ground chuck or lean ground; beef\" }, { \"amt\": { \"qty\": \"1\", \"unit\": \"\" }, \"item\": \"Onion; large, chopped\" }, { \"amt\": { \"qty\": \"1\", \"unit\": \"can\" }, \"item\": \"Kidney beans; (12 oz)\" }, { \"amt\": { \"qty\": \"1\", \"unit\": \"can\" }, \"item\": \"Tomato soup; undiluted\" }, { \"amt\": { \"qty\": \"1\", \"unit\": \"teaspoon\" }, \"item\": \"Salt\" }, { \"amt\": { \"qty\": \"1\", \"unit\": \"tablespoon\" }, \"item\": \"Chili powder; (or to taste)\" }, { \"amt\": { \"qty\": \"\", \"unit\": \"\" }, \"item\": \"Hot pepper sauce; to taste\" } ] }, \"directions\": { \"step\": \" Brown the meat in a little butter and cook until the meat is brown -- about\\n 10 minutes. Add all other ingredients and let simmer for 30 minutes. Your\\n choice of hot sauce may be added to taste.\\n \\n Recipe by: MasterCook Archives\\n \\n Posted to recipelu-digest Volume 01 Number 577 by Rodeo46898\\n <Rodeo46898@aol.com> on Jan 22, 1998\\n \\n\" } } ] }";
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    private Add getAddClass(String Json) {
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        Add a = g.fromJson(Json, Add.class);
        return a;
    }

    public String setAdd(String str) {
        if (str!=null && !"".equals(str)) {
            try {
                execSQL a = new execSQL();
                Add add = getAddClass(str);
                Directions directions = add.getDirections();
                
                Head head = add.getHead();
                Categories categories = head.getCategories();
                
                Ingredients ingredients = add.getIngredients();
                
                List<IngDiv> ingdiv = ingredients.getIngDiv();

                int IngredientID = a.getIngredientID();
                int DirectionID = a.getDirectionID();

                int RecipeID = a.dbRecipes(head.getTitle(), IngredientID, DirectionID, head.getYield());
                List<Ing> IngList = new ArrayList<Ing>();
                int tmpIngredientID=IngredientID;
                for (int i=0;i<ingdiv.size();i++){
                    IngList=ingdiv.get(i).getIng();
                    for (int j=0;j<IngList.size();j++){
                        a.dbItems(IngList.get(j).getItem());
                        a.dbUnits(IngList.get(j).getAmt().getUnit());
                        a.dbIngredients(IngredientID, IngList.get(j).getAmt().getQty(), IngList.get(j).getAmt().getUnit(), IngList.get(j).getItem());
                    }
                    a.dbRecipeIngredients(RecipeID, IngredientID, ingdiv.get(i).getTitle());
                    IngredientID++;
                }
                a.dbDirections(DirectionID, 0, directions.getStep());

                for (int i = 0; i < categories.getCat().size(); i++) {
                    a.dbRecipeCategories(RecipeID, categories.getCat().get(i));
                    a.dbCategories(categories.getCat().get(i));
                }
                return "OK";
            } catch (Exception e) {
                return "Err: "+e.toString();
            }
        } else return "Empty Msg!";
    }

}
