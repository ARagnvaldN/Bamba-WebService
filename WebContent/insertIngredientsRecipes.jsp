<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="se.kth.nylun.bamba.model.Recipe" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="se.kth.nylun.bamba.model.Ingredient" %>
<%@ page import="se.kth.nylun.bamba.DB.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recipe Manager</title>
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

<jsp:include page="navigator.jsp" />

 <table align="center">
  <tr>
	  <th>ID</th>
	  <th>Skapat</th>
	  <th>Namn</th>
	  <th>Beskrivning</th>
	  <th>Instruktioner</th>
  </tr> 
   
  <%	String sID = request.getParameter("id");
  		Recipe recipe = DBFacade.getRecipeByID(Integer.parseInt(sID));
		 %>
      
      <tr>
      	  <form action=${pageContext.request.contextPath}/RecipeController method="post">
	      <td><%=recipe.getRecipeId() %></td>
	      <td><%=recipe.getCreationDate() %></td>
	      <td><%=recipe.getName() %></td>
	      <td><%=recipe.getDescription() %></td>
	      <td><% String s =recipe.getInstructions();
	      		String[] sarr = s.split("#"); 
	      		for(int i=0;i<sarr.length;i++)
	      			out.println(sarr[i]+"\n");%></td>
	      
	      </form>
      </tr>
      
      </table>
      
      <table align="center">
    <tr>
      <th>ID</th>
	  <th>Ingrediens</th>
	  <th>Mängd</th>
	  <th>Enhet</th>
  	</tr> 
  	
  	       <%	ArrayList<Ingredient> list = DBFacade.getAllIngredients();
	Iterator<Ingredient> it = list.iterator(); %>
  	
  	<tr>
  	<form action=${pageContext.request.contextPath}/RecipeController method="post">
  	<td>#</td>
  	<td>
	  	<select name="item">
		<%while(it.hasNext()){
		Ingredient item = it.next();%>
	        <option value="<%=item.getIngredientId() %>"><%=item.getName() %></option>
		<%}%>
		</select>
	</td>
	<td>
		<input type="text" name="quantity">
	</td>
	<td>
		st/l/kg
	</td>
	<td>
		<input type="hidden" name="recipe" value=<%=recipe.getRecipeId() %>>
		<input type="submit" name="addItemToRecipe" value="Lägg till">
	</td>
  	</tr>
      

<%ArrayList<Ingredient> list2 = recipe.getIngredients();
it = list2.iterator();
while (it.hasNext()) {
		Ingredient item = it.next(); %>
		
		<tr>
      	  <form action=${pageContext.request.contextPath}/RecipeController method="post">
	      <td><%=item.getIngredientId() %></td>
	      <td><%=item.getName() %></td>
	      <td><%=item.getQuantity() %></td>
	      <td><%=item.getUnitString() %></td>
	      <input type="hidden" name="id" value=<%=item.getIngredientId() %>>
	      </form>
      </tr>
  
  <%} %>
 </table>
  

</body>
</html>