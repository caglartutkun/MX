/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Caglar
 */
public class Amt {
    
    @SerializedName("qty")
@Expose
private String qty;
@SerializedName("unit")
@Expose
private String unit;

public String getQty() {
return qty;
}

public void setQty(String qty) {
this.qty = qty;
}

public String getUnit() {
return unit;
}

public void setUnit(String unit) {
this.unit = unit;
}
    
}
