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
public class Ingredients {

    @SerializedName("ing-div")
    @Expose
    private List<IngDiv> ingDiv = new ArrayList<IngDiv>();

    @SerializedName("ing")
    @Expose
    private List<Ing> ing = new ArrayList<Ing>();

    public List<Ing> getIng() {
        return ing;
    }

    public void setIng(List<Ing> ing) {
        this.ing = ing;
    }

    public List<IngDiv> getIngDiv() {
        return ingDiv;
    }

    public void setIngDiv(List<IngDiv> ingDiv) {
        this.ingDiv = ingDiv;
    }
}
