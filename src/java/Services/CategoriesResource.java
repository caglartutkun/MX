/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.getCategories;
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
@Path("categories")
public class CategoriesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CategoriesResource
     */
    public CategoriesResource() {
    }

    /**
     * Retrieves representation of an instance of Services.CategoriesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String categories() {
        getCategories a = new getCategories();
        return a.getAllCategoriesJson();
    }

}
