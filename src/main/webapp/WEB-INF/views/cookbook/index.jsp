<%@ include file="/WEB-INF/includes/header.jspf" %>
	<title>DB</title>
</head>
<body>
	<h1>Cookbook Recipes</h1>
	<c:if test="${not empty param.message}">
		<span style="color:green;">${param.message}</span>
	</c:if>
	<c:if test="${not empty param.error}">
		<span style="color:red;">${param.error}</span>
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
<%@ include file="/WEB-INF/includes/footer.jspf" %>