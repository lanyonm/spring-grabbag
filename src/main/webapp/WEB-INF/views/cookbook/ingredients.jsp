<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Ingredients</title>
</head>
<body>
	<h1>All Ingredients</h1>
	<c:if test="${not empty param.message}">
		<div class="alert alert-success">${param.message}</div>
	</c:if>
	<c:if test="${not empty param.error}">
		<div class="alert alert-danger">${param.error}</div>
	</c:if>
	<ul>
		<c:forEach items="${ingredients}" var="ingredient">
			<li>${ingredient.name} - <em>${ingredient.description}</em> | <a href="ingredient/${ingredient.id}/edit">edit</a> | <a href="ingredient/${ingredient.id}/delete" class="delete">delete</a></li>
		</c:forEach>
	</ul>
	<a href="ingredient/0/edit">Add Ingredient</a>
</body>
</html>