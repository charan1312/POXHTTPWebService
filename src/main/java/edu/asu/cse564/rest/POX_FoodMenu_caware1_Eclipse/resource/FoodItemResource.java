package edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.asu.cse564.rest.POX_FoodMenu_caware1_Eclipse.service.FoodItemService;

@Path("/FoodItem")
//@Singleton
public class FoodItemResource {
    
    public FoodItemResource() {
        // TODO Auto-generated constructor stub
        //System.out.println("RESOURCE CREATED");
    }
    private FoodItemService foodItemService = new FoodItemService();
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getOutput(String input){
        //System.out.println("Request came");
        //System.out.println(input);
        int res = foodItemService.prepareOutput(input);
        return foodItemService.getResponse(res);
        // return null;
    }
}
