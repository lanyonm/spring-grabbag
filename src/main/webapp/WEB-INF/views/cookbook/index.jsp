<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Cookbook</title>
</head>
<body>
	<h1>Cookbook Recipes</h1>
	<c:if test="${not empty param.message}">
		<div class="alert alert-success">${param.message}</div>
	</c:if>
	<c:if test="${not empty param.error}">
		<div class="alert alert-danger">${param.error}</div>
	</c:if>
	<ul>
		<c:forEach items="${recipes}" var="recipe">
			<li><a href="recipe/${recipe.id}">${recipe.name}</a> | <a href="recipe/${recipe.id}/edit">edit</a></li>
		</c:forEach>
	</ul>
	<article>
		<div>
			<a href="recipe/0/edit">Add Recipe</a> | <a href="ingredients" title="All Ingredients">All Ingredients</a>
		</div>
	</article>
</body>
</html>
