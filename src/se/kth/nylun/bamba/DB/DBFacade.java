package se.kth.nylun.bamba.DB;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import se.kth.nylun.bamba.model.Ingredient;
import se.kth.nylun.bamba.model.Recipe;

public class DBFacade {
	
	
/*
	public static ArrayList<Item> getAllItems(){
		
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		try{
			
			Connection conn = DBManager.getConnection();
			Statement stmnt = conn.createStatement();
			
			ResultSet result = stmnt.executeQuery("SELECT * FROM shopitems");
			
			while(result.next()){
				Item item = new ItemDB(
						result.getInt("itemid"),
						result.getString("name"),
						result.getString("description"),
						result.getDouble("price"),
						result.getInt("stock"),
						result.getString("category"));
				
				itemList.add(item);
			}
			
			DBManager.releaseConnection(conn);
			conn = null;
		} catch(Exception ex){
			
		}
		
		return itemList;
	}
	
public static ArrayList<Item> getAllItemsByCategory(){
		
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		try{
			
			Connection conn = DBManager.getConnection();
			Statement stmnt = conn.createStatement();
			
			ResultSet result = stmnt.executeQuery("SELECT * FROM shopitems order by category");
			
			while(result.next()){
				Item item = new ItemDB(
						result.getInt("itemid"),
						result.getString("name"),
						result.getString("description"),
						result.getDouble("price"),
						result.getInt("stock"),
						result.getString("category"));
				
				itemList.add(item);
			}
			
			DBManager.releaseConnection(conn);
			conn = null;
		} catch(Exception ex){
			
		}
		
		return itemList;
	}

	public static void addItem(Item item) {
		
		try{
			Connection conn = DBManager.getConnection();
			Statement stmnt = conn.createStatement();
			
			boolean result = stmnt.execute("INSERT INTO shopitems " +
										"VALUES(DEFAULT,'" + item.getName() +
										"', '" + item.getDescription() +
										"', " + item.getPrice() +
										", " + item.getStock() + 
										", '" + item.getCategory() + ");"
										);
			
			DBManager.releaseConnection(conn);
			conn = null;
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
*/
	public static boolean addIngredient(Ingredient item){
		
		boolean result = false;
		try{
			Connection conn = DBManager.getConnection();
			Statement stmnt = conn.createStatement();
			
			result = stmnt.execute("INSERT INTO Ingredients " +
										"VALUES('" + item.getName() +
										"', '" + item.getDescription() +
										"', " + item.getCategory() +
										", " + item.getMeanLifeTime() + 
										", DEFAULT," +
										+ item.getUnit() + ");"
										);
			
			DBManager.releaseConnection(conn);
			conn = null;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Ingredient getIngredientByID(int id){
		
		Ingredient ingredient = null;
		
		try{
			Connection conn = DBManager.getConnection();
			
			Statement stmnt = conn.createStatement();
			
			ResultSet result = stmnt.executeQuery("SELECT * FROM Ingredients " +
					"WHERE id= "+ id +";");
			if(result == null)throw new IOException();
			while(result.next()){
				ingredient = new Ingredient(
					result.getInt("id"),
					result.getString("name"),
					result.getString("description"),
					result.getInt("category"),
					result.getInt("meanLifeTime"),
					0,					//result.getDouble("quantity"),
					result.getInt("unit"));
			}
			
			DBManager.releaseConnection(conn);
			conn = null;
			
		} catch(Exception e){
			return new Ingredient(1,e.getMessage(),e.getLocalizedMessage(),2,5,1,1);
		}
		
		return ingredient;
	}
	
public static Recipe getRecipeByID(int id){
		
		Recipe recipe = null;
		
		try{
			Connection conn = DBManager.getConnection();
			Statement stmnt = conn.createStatement();
			
			//Get recipe data 
			ResultSet result = stmnt.executeQuery(
					"SELECT * FROM Recipes " +
					"WHERE id="+ id +";");
			
			while(result.next()){
				recipe = new Recipe(
					result.getInt("id"),
					result.getInt("authorID"),
					result.getDate("creationDate"),
					result.getString("name"),
					result.getString("description"),
					result.getString("instructions"),
					null);
			}
			
			//Get ingredients
			result = stmnt.executeQuery(
					"SELECT * " +
					"FROM Ingredients i " +
					"INNER JOIN RecipeIngredients ri " +
					"ON i.id = ri.ingredientId " +
					"WHERE ri.recipeId =" + id + ";");
			
			ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
			
			while(result.next()){
				Ingredient i = new Ingredient(
					result.getInt("id"),
					result.getString("name"),
					result.getString("description"),
					result.getInt("category"),
					result.getInt("meanLifeTime"),
					result.getDouble("quantity"),
					result.getInt("unit"));
				
				ingredients.add(i);
			}
			
			recipe.setIngredients(ingredients);
			
			DBManager.releaseConnection(conn);
			conn = null;
			
		} catch(Exception e){
			return new Recipe(1,1,null,"Error",e.toString(),e.getMessage(),null);
		}
		
		return recipe;
	}

public static ArrayList<Ingredient> getAllIngredients() {
	
	ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	try{
		Connection conn = DBManager.getConnection();
		Statement stmnt = conn.createStatement();
		
		ResultSet result = stmnt.executeQuery("SELECT * FROM Ingredients;");
		
		while(result.next()){
			Ingredient ingredient = new Ingredient(
				result.getInt("id"),
				result.getString("name"),
				result.getString("description"),
				result.getInt("category"),
				result.getInt("meanLifeTime"),
				0,					//result.getDouble("quantity"),
				result.getInt("unit"));
			
			ingredients.add(ingredient);
		}
		
		DBManager.releaseConnection(conn);
		conn = null;
		
	} catch(Exception e){
		e.printStackTrace();
	}
	
	return ingredients;
}

public static ArrayList<Recipe> getAllRecipes() {


	ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	try{
		Connection conn = DBManager.getConnection();
		Statement stmnt = conn.createStatement();
		
		//Get recipe data 
		ResultSet result = stmnt.executeQuery(
				"SELECT * FROM Recipes;");
		
		while(result.next()){
			Recipe recipe = new Recipe(
				result.getInt("id"),
				result.getInt("authorID"),
				result.getDate("creationDate"),
				result.getString("name"),
				result.getString("description"),
				result.getString("instructions"),
				null);
			
			recipes.add(recipe);
		}
		
		//Get all ingredients for all recipes
		for(Recipe r: recipes){
			//Get ingredients
			result = stmnt.executeQuery(
					"SELECT * " +
					"FROM Ingredients i " +
					"INNER JOIN RecipeIngredients ri " +
					"ON i.id = ri.ingredientId " +
					"WHERE ri.recipeId =" + r.getRecipeId() + ";");
			
			ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
			
			while(result.next()){
				Ingredient i = new Ingredient(
					result.getInt("id"),
					result.getString("name"),
					result.getString("description"),
					result.getInt("category"),
					result.getInt("meanLifeTime"),
					result.getDouble("quantity"),
					result.getInt("unit"));
				
				ingredients.add(i);
			}
			
			r.setIngredients(ingredients);
		}
		
		DBManager.releaseConnection(conn);
		conn = null;
		
	} catch(Exception e){
		e.printStackTrace();
	}
	
	return recipes;
}

public static boolean addRecipe(Recipe recipe) {
	
	boolean result = false;
	try{
		Connection conn = DBManager.getConnection();
		Statement stmnt = conn.createStatement();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date utilDate = recipe.getCreationDate();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		System.out.println(sqlDate.toString());
		
		result = stmnt.execute("INSERT INTO Recipes " +
									"VALUES(DEFAULT,'" +
									sqlDate +"', '"+
									recipe.getName() +"', '" +
									recipe.getDescription() +"', '"+
									recipe.getInstructions() +"', " +
									"1);"
									);
		
		DBManager.releaseConnection(conn);
		conn = null;
	} catch(Exception e){
		e.printStackTrace();
	}
	
	return result;
	
}

public static boolean addItemToRecipe(int ingredientID, int recipeID, double quantity){
	
	
	boolean result = false;
	try{
		Connection conn = DBManager.getConnection();
		Statement stmnt = conn.createStatement();
		
		result = stmnt.execute("INSERT INTO RecipeIngredients " +
									"VALUES(" + recipeID +
									", " + ingredientID +
									", " + quantity+
									");"
									);
		
		DBManager.releaseConnection(conn);
		conn = null;
	} catch(Exception e){
		e.printStackTrace();
	}
	
	return false;
}

}
	
