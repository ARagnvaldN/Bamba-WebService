<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="se.kth.nylun.bamba.model.Recipe" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Date" %>
<%@ page import="se.kth.nylun.bamba.model.Ingredient" %>
<%@ page import="se.kth.nylun.bamba.DB.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

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
	  <th>Instruktioner (# för punkter)</th>
  </tr> 
  
  <tr>
      	  <form action=${pageContext.request.contextPath}/RecipeController method="post">
      	  <td>#</td>
      	  <td><%SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      			out.print(sdf.format(new Date()));%></td>
	      <td><input type="text" name="name"></td>
	      <td><input type="text" name="desc" style="width:100%"></td>
	      <td><input type="text" name="instr" style="width:100%"></td>
	      
	      <td><input type="submit" name="addRecipe" value="Lägg till recept"></td>
	      </form>
      </tr>
   
  <%	ArrayList<Recipe> list = DBFacade.getAllRecipes();
	Iterator<Recipe> it = list.iterator(); %>
<% while (it.hasNext()) {
		Recipe item = it.next(); %>
      
      
      <tr>
      	  <form action=${pageContext.request.contextPath}/RecipeController method="post">
	      <td><%=item.getRecipeId() %></td>
	      <td><%=item.getCreationDate() %></td>
	      <td><%=item.getName() %></td>
	      <td><%=item.getDescription() %></td>
	      <td><% String s =item.getInstructions();
	      		String[] sarr = s.split("#"); 
	      		for(int i=0;i<sarr.length;i++)
	      			out.println(sarr[i]+"\n");%></td>
	      
	      <td><input type="submit" name="addItem" value="Lägg till ingredienser"></td>
	      <input type="hidden" name="id" value=<%=item.getRecipeId() %>>
	      </form>
      </tr>
      
<% }%>
  
 </table>
  

</body>
</html>