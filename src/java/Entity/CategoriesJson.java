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
public class CategoriesJson {

    @SerializedName("results")
    @Expose
    private int results;
    @SerializedName("cat")
    @Expose
    private List<String> cat = new ArrayList<String>();
    
    public int getResults(){
        return results;
    }
    
    public void setResults(int Results){
        this.results=Results;
    }
    
    public List<String> getCat() {
        return cat;
    }

    public void setCat(List<String> cat) {
        this.cat = cat;
    }
}
