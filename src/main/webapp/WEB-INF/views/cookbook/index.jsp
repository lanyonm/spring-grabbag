<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
	<title>DB</title>
</head>
<body>
	<h1>Cookbook Recipes</h1>
	<ul>
		<c:forEach items="${recipes}" var="recipe">
			<li><a href="recipe/${recipe.id}">${recipe.name}</a></li>
		</c:forEach>
	</ul>
	<article>
		<div>
			<a href="ingredients" title="All Ingredients">All Ingredients</a>
		</div>
	</article>
</body>
</html>