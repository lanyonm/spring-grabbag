<%@ include file="/WEB-INF/includes/header.jspf" %>
	<title>DB</title>
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
<%@ include file="/WEB-INF/includes/footer.jspf" %>