<%@ include file="/WEB-INF/includes/header.jspf" %>
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
<%@ include file="/WEB-INF/includes/footer.jspf" %>