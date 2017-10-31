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
public class Directions {
    @SerializedName("step")
@Expose
private String step;

public String getStep() {
return step;
}

public void setStep(String step) {
this.step = step;
}
}
