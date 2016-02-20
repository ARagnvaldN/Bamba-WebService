package se.kth.nylun.bamba.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.kth.nylun.bamba.DB.DBFacade;
import se.kth.nylun.bamba.model.Recipe;

@WebServlet("/RecipeController")
public class RecipeController extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
			
		request.setCharacterEncoding("UTF-8");
		
		
		if(request.getParameter("addRecipe") != null){
			
			String name = request.getParameter("name");
			String desc = request.getParameter("desc");
			String instr = request.getParameter("instr");
			
			Recipe recipe = new Recipe(0, 0, new Date(), name, desc, instr, null);
			
			DBFacade.addRecipe(recipe);
			
			response.sendRedirect("insertRecipes.jsp");
		
		} else if(request.getParameter("addItem") != null){
			
			String id = request.getParameter("id");

			//Recipe recipe = DBFacade.getRecipeByID(Integer.parseInt(id));
			
			response.sendRedirect("insertIngredientsRecipes.jsp?id="+id);
		
		} else if(request.getParameter("addItemToRecipe") != null){
			
			String itemID = request.getParameter("item");
			String recipeID = request.getParameter("recipe");
			String quantity = request.getParameter("quantity");
			
			DBFacade.addItemToRecipe(Integer.parseInt(itemID),
									 Integer.parseInt(recipeID),
									 Double.parseDouble(quantity));
			
			response.sendRedirect("insertIngredientsRecipes.jsp?id="+recipeID);
		}
		
		
		
	}
}
