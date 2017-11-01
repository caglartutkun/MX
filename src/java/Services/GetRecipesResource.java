/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.HeadJson;
import Utils.getRecipe;
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
@Path("getRecipes")
public class GetRecipesResource {

    @Context
    private UriInfo context;
    private getRecipe a = new getRecipe();
    /**
     * Creates a new instance of GetRecipesResource
     */
    public GetRecipesResource() {
    }

    /**
     * Retrieves representation of an instance of Services.GetRecipesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getJson() {
        //TODO return proper representation object
        //return "Example: {\"id\":\"28\", \"title\": \"Amaretto Cake\", \"categories\": { \"cat\": [ \"Liquor\", \"Cakes\", \"Cake mixes\" ] }, \"yield\": \"1\" }";
        return "Example: {\"id\":\"28\"}";
    }

    /**
     * PUT method for updating or creating an instance of GetRecipesResource
     * @param content representation for the resource
     * @return 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putJson(String content) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return a.getRecipeJson(gson.fromJson(content, HeadJson.class).getId());
    }
}
