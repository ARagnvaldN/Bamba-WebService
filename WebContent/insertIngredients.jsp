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
<title>Ingredient Manager</title>
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

<jsp:include page="navigator.jsp" />

 <table align="center">
  <tr>
	  <th>ID</th>
	  <th>Kategori</th>
	  <th>Namn</th>
	  <th>Beskrivning</th>
	  <th>Enhet</th>
	  <th>Hållbarhet(dagar)</th>
  </tr> 
  <tr>
  	<form action=${pageContext.request.contextPath}/IngredientController method="post">
  	<td></td>
  	<td>
  		<select name="category">
  			<option value="0" selected>Frukt och grönt</option>
  			<option value="1">Chark</option>
  			<option value="2">Mejeri</option>
  			<option value="3">Frys</option>
  			<option value="4">Torr</option>
  			<option value="5">Snacks</option>
  		</select>
  	</td>
  	<td><input type="text" name="name"></td>
  	<td><input type="text" name="desc"></td>
  	<td><select name="unit">
  			<option value="0" selected>st.</option>
  			<option value="1">liter</option>
  			<option value="2">kg</option>
  		</select></td>
  	<td><input type="text" name="mlt" size="5"></td>
  	<td><input type="submit" name="addItem" value="Lägg till"></td>
  	</form>
  </tr>
  
  <%	ArrayList<Ingredient> list = DBFacade.getAllIngredients();
	Iterator<Ingredient> it = list.iterator(); %>
<% while (it.hasNext()) {
		Ingredient item = it.next(); %>
      
      <tr>
      	  <form action=${pageContext.request.contextPath}/IngredientController method="post">
	      <td><%=item.getIngredientId() %></td>
	      <td><%=item.getCategoryString() %></td>
	      <td><%=item.getName() %></td>
	      <td><%=item.getDescription() %></td>
	      <td><%=item.getUnitString() %></td>
	      <td><%=item.getMeanLifeTime() %></td>
	      <input type="hidden" name="id" value=<%=item.getIngredientId() %>>
	      </form>
      </tr>
      
<% }%>
  
 </table>
  

</body>
</html>