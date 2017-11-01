/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdapterServices;

import Entity.*;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Caglar
 */
public class execSQL {

    private final MySQLConnection mc = new MySQLConnection();
    private Connection conn = mc.ConnectDB();
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public int dbRecipes(String Title, int IngredientID, int DirectionID, String Yield) {
        String sql = "insert into Recipes (Title, IngredientID, DirectionID, Yield) VALUES (?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, Title);
            pst.setInt(2, IngredientID);
            pst.setInt(3, DirectionID);
            pst.setString(4, Yield);
            pst.executeUpdate();
            return getID("select MAX(ID) as 'ID' from Recipes");
        } catch (SQLException e) {
            return -1;
        }
    }

    public String dbIngredients(int IngredientID, String Quantity, String Unit, String Item) {

        String sql = "insert into Ingredients (IngredientID, Quantity, Unit, Item) VALUES (?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, IngredientID);
            pst.setString(2, Quantity);
            pst.setString(3, Unit);
            pst.setString(4, Item);
            pst.executeUpdate();
            return String.valueOf(getID("select MAX(ID) as 'ID' from Ingredients"));
        } catch (SQLException e) {
            return e.getLocalizedMessage();
        }
    }

    public String dbDirections(int DirectionID, int StepOrder, String Direction) {
        String sql = "insert into Directions (DirectionID, StepOrder, Direction) VALUES (?,?,?);";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, DirectionID);
            pst.setInt(2, StepOrder);
            pst.setString(3, Direction);
            pst.executeUpdate();
            return String.valueOf(getID("select MAX(ID) as 'ID' from Directions"));
        } catch (SQLException e) {
            return e.getLocalizedMessage();
        }
    }

    public String dbRecipeIngredients(int RecipeID, int IngredientID, String Title) {
        String sql = "insert into RecipeIngredients (RecipeID, IngredientID, Title) VALUES (?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, RecipeID);
            pst.setInt(2, IngredientID);
            pst.setString(3, Title);
            pst.executeUpdate();
            return String.valueOf(getID("select MAX(ID) as 'ID' from RecipeIngredients"));
        } catch (SQLException e) {
            return e.getLocalizedMessage();
        }
    }

    public String dbRecipeCategories(int RecipeID, String Category) {
        String sql = "insert into RecipeCategories (RecipeID, Category) VALUES (?,?);";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, RecipeID);
            pst.setString(2, Category);
            pst.executeUpdate();
            return String.valueOf(getID("select MAX(ID) as 'ID' from RecipeCategories"));
        } catch (SQLException e) {
            return e.getLocalizedMessage();
        }
    }

    public String dbCategories(String Title) {
        try {
            String sql = "insert into Categories (title) VALUES (?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, Title);
            pst.executeUpdate();
            return "OK";
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    public String dbItems(String Title) {
        try {
            String sql = "insert into Items (Title) VALUES (?);";
            pst = conn.prepareStatement(sql);
            pst.setString(1, Title);
            pst.executeUpdate();
            return "OK";
        } catch (SQLException e) {
            return e.getLocalizedMessage();
        }
    }

    public String dbUnits(String Title) {
        if (!"".equals(Title)) {
            try {
                String sql = "insert into Units (Title) VALUES (?);";
                pst = conn.prepareStatement(sql);
                pst.setString(1, Title);
                pst.executeUpdate();
                return "OK";
            } catch (SQLException e) {
                return e.getLocalizedMessage();
            }
        } else {
            return "Unit Empty";
        }
    }

    public int getID(String sql) {/**/
        int id = 0;
        try {
            rs = execRS(sql);
            while (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (Exception e) {
            id = -1;
        }
        return id;
    }

    public int getIngredientID() {/**/
        int id = 100;
        try {
            rs = execRS("select case count(*) when 0 then 100 else MAX(IngredientID)+1 end as ID from recipes");
            while (rs.next()) {
                id = rs.getInt("ID");
            }

        } catch (Exception e) {
            id = -1;
        }
        return id;
    }

    public int getDirectionID() {/**/
        int id = 0;
        try {
            rs = execRS("select case count(*) when 0 then 1000 else max(DirectionID)+1 end as ID from recipes");
            while (rs.next()) {
                id = rs.getInt("ID");
            }
            return id;
        } catch (Exception e) {
            return -1;
        }
    }

    private ResultSet execRS(String sql) {
        try {
            conn = mc.ConnectDB();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (SQLException e) {
        }
        return rs;
    }

    public ResultSet getHeadAll() {
        String sql = "select r.ID as 'id', r.Title as 'title',(select GROUP_CONCAT(rr.Category SEPARATOR ',') from recipecategories rr where rr.RecipeID=r.ID) as 'categories'  ,r.Yield as 'yield' from recipes r ";
        return execRS(sql);
    }

    public ResultSet getDirections(int RecipeID) {
        try {
            String sql = "SELECT d.Direction as 'direction' FROM Directions d, recipes r where r.DirectionID=d.DirectionID and r.ID=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, RecipeID);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            return null;
        }
    }

    public ResultSet getRecipeIngredients(int RecipeID) {
        String sql = "SELECT ingredientid,title from recipeingredients where recipeid=? ";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, RecipeID);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            return null;
        }
        return rs;
    }

    public ResultSet getIngredients(int IngredientID) {
        String sql = "SELECT quantity, unit, item FROM Ingredients where ingredientid=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, IngredientID);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            return null;
        }
        return rs;
    }

    public ResultSet getHeadFiltered_old(String title, Categories category, String yield) {
        String sql = "select r.ID as 'id', r.Title as 'title',(select GROUP_CONCAT(rr.Category SEPARATOR ',') from recipecategories rr where rr.RecipeID=r.ID) as 'categories',r.Yield as 'yield' from recipes r where lower(r.title) like ? and r.yield like ? ;";
        int curr = 1;
        try {
            pst = conn.prepareStatement(sql);
            if (title == null || title == "") {
                pst.setString(curr, "%");
            } else {
                pst.setString(curr, "%" + title.toLowerCase() + "%");
            }
            curr++;
            if (yield == null || yield == "") {
                pst.setString(curr, "%");
            } else {
                pst.setString(curr, "%" + yield + "%");
            }
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }

    public ResultSet getHeadFiltered(String title, Categories category, String yield) {
        String sql = "select r.ID as 'id', r.Title as 'title',(select GROUP_CONCAT(rr.Category SEPARATOR ',') from recipecategories rr where rr.RecipeID=r.ID) as 'categories',r.Yield as 'yield' from recipes r where lower(r.title) like ? and r.yield like ? ";
        int cur = 1, catSize = 0;
        String tmp = "", sep = "";
        if (category != null) {
            catSize = category.getCat().size();
        }
        if (catSize > 0) {

            for (int i = 0; i < catSize; i++) {
                tmp += sep + "?";
                sep = ",";
            }
            sql = "select r.ID as 'id', r.Title as 'title',(select GROUP_CONCAT(rr.Category SEPARATOR ',') from recipecategories rr where rr.RecipeID=r.ID) as 'categories',r.Yield as 'yield' from recipes r where r.ID in (select distinct RecipeID from recipecategories where LOWER(Category) in (" + tmp + ")) and (lower(r.title) like ? and r.yield like ? );";

        }

        try {
            pst = conn.prepareStatement(sql);
            if (title == null || title == "") {
                pst.setString(catSize+cur, "%");
            } else {
                pst.setString(catSize+cur, "%" + title.toLowerCase() + "%");
            }
            cur++;
            if (yield == null || yield == "") {
                pst.setString(catSize+cur, "%");
            } else {
                pst.setString(catSize+cur, "%" + yield + "%");
            }
            cur=1;
            if (catSize > 0) {
                if (title == null && yield == null) {
                    sql = "select r.ID as 'id', r.Title as 'title',(select GROUP_CONCAT(rr.Category SEPARATOR ',') from recipecategories rr where rr.RecipeID=r.ID) as 'categories',r.Yield as 'yield' from recipes r where r.ID in (select distinct RecipeID from recipecategories where LOWER(Category) in (" + tmp + ")) ;";
                    pst = conn.prepareStatement(sql);
                }
                for (String cat : category.getCat()) {
                    pst.setString(cur, cat.toLowerCase());
                    cur++;
                }
            }
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            return null;
        }

    }

    public ResultSet getRecipe(int RecipeID) {
        try {
            String sql = "?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, RecipeID);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            return null;
        }
    }

    public ResultSet getHeadbyID(int RecipeID) {
        try {
            String sql = "select r.ID as 'id', r.Title as 'title',(select GROUP_CONCAT(rr.Category SEPARATOR ',') from recipecategories rr where rr.RecipeID=r.ID) as 'categories'  ,r.Yield as 'yield' from recipes r where r.ID=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, RecipeID);
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            return null;
        }
    }

}
