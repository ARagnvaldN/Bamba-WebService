package se.kth.nylun.bamba.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import se.kth.nylun.bamba.DB.DBFacade;
import se.kth.nylun.bamba.model.*;

@Path("/hello")
public class BambaServiceFacade {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String seyHi(){
		
		return "<html>" +
				"<header>" +
				"</header>" +
				"<body>" +
				"Hi!" +
				"</body>" +
				"</html>";
	}
	
	//====================================================================
	// Method querying the database for an ingredient, by ID.
	//====================================================================
	@GET
	@Path("/ingredient")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Ingredient getIngredientById(@QueryParam("id") int id){
		
		return DBFacade.getIngredientByID(id);
	}
	
	//====================================================================
	// Method querying the database for ALL ingredients.
	//====================================================================
	@GET
	@Path("/allIngredients")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Ingredient> getAllIngredients(){
		
		return DBFacade.getAllIngredients();
	}
	
	//====================================================================
	// Method querying the database for a recipe, by ID.
	//====================================================================
	@GET
	@Path("/recipe")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Recipe getRecipeById(@QueryParam("id") int id){
		
		return DBFacade.getRecipeByID(id);
	}
	
	//====================================================================
	// Method querying the database for ALL recipes.
	//====================================================================
	@GET
	@Path("/allRecipes")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Recipe> getAllRecipes(){
		
		return DBFacade.getAllRecipes();
	}
	
}
