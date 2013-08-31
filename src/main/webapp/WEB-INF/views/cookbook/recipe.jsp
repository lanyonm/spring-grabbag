<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>${recipe.name}</title>
</head>
<body>
	<div>
		<h1>${recipe.name}</h1>
		<a href="${recipe.id}/edit">edit</a> | <a href="${recipe.id}/delete">delete</a>
	</div>
	<article>
		<ul>
			<c:forEach items="${recipe.ingredients}" var="ingredient">
				<li>${ingredient.volume} ${ingredient.name}</li>
			</c:forEach>
		</ul>
	</article>
</body>
</html>