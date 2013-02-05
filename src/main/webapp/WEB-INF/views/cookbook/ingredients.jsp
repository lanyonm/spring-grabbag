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
	<h1>All Ingredients</h1>
	<c:if test="${not empty param.message}">
		<span style="color:green;">${param.message}</span>
	</c:if>
	<c:if test="${not empty param.error}">
		<span style="color:red;">${param.error}</span>
	</c:if>
	<ul>
		<c:forEach items="${ingredients}" var="ingredient">
			<li>${ingredient.name} - <em>${ingredient.description}</em> | <a href="ingredient/${ingredient.id}/edit">edit</a> | <a href="ingredient/${ingredient.id}/delete" class="delete">delete</a></li>
		</c:forEach>
	</ul>
	<a href="ingredient/0/edit">Add Ingredient</a>
</body>
</html>