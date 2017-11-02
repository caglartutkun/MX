/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import AdapterServices.execSQL;
import Entity.Categories;
import Entity.Head;
import Entity.HeadJson;
import Utils.filterRecipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Caglar
 */
@Path("services/recipe/filter")
public class FilterResource {

    @Context
    private UriInfo context;
    private filterRecipe a = new filterRecipe();
    private execSQL ex = new execSQL();
    /**
     * Creates a new instance of FilterResource
     */
    public FilterResource() {
    }

    /**
     * Retrieves representation of an instance of Services.FilterResource
     * @return an instance of java.lang.String
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String filter(String content) {
        return a.getResults(content);
        //TODO return proper representation object
    }

}
