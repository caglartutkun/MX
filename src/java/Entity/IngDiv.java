/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 *
 * @author Caglar
 */
public class IngDiv {

    @SerializedName("ingredientid")
    @Expose
    private int ingredientid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("ing")
    @Expose
    private List<Ing> ing = new ArrayList<Ing>();

    public int getIngredientId() {
        return ingredientid;
    }

    public void setIngredientId(int ingredientid) {
        this.ingredientid = ingredientid;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Ing> getIng() {
        return ing;
    }

    public void setIng(List<Ing> ing) {
        this.ing = ing;
    }
}
