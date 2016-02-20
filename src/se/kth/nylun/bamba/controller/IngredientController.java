package se.kth.nylun.bamba.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.kth.nylun.bamba.DB.DBFacade;
import se.kth.nylun.bamba.model.Ingredient;

@WebServlet("/IngredientController")
public class IngredientController extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
			
		request.setCharacterEncoding("UTF-8");
		
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			String desc = request.getParameter("desc");
			String unit = request.getParameter("unit");
			String mlt = request.getParameter("mlt");
		
			Ingredient ingredient = new Ingredient(0, name, desc, Integer.parseInt(category),
													Integer.parseInt(mlt), 0, Integer.parseInt(unit));
			
			DBFacade.addIngredient(ingredient);
			
			response.sendRedirect("insertIngredients.jsp");
	}
}
