<%@ include file="/WEB-INF/includes/header.jspf" %>
	<title>${ recipe.id == 0 ? 'Add' : 'Edit' } Recipe</title>
</head>
<body>
	<h1>${ recipe.id == 0 ? 'Add' : 'Edit' } Recipe</h1>
	<c:if test="${not empty param.error}">
		<span style="color:red;">${param.error}</span>
	</c:if>
	<div style="float:left;width:50%;">
		<form:form commandName="recipe">
			<table>
				<tr>
					<td>Name:</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><form:input path="description" /></td>
				</tr>
				<tr>
					<td style="vertical-align:text-top;">Ingredients:</td>
					<td>
						<table>
							<c:forEach items="${recipe.ingredients}" var="recipeIngredient" varStatus="status">
								<tr>
									<input type="hidden" name="ingredient${status.index}" value="${recipeIngredient.id}" />
									<td>${recipeIngredient.name}</td>
									<td>${recipeIngredient.volume}</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="Save" />
					</td>
				</tr>
			</table>
		</form:form>
	</div>
	<div style="float:left;width:50%;">
		All Ingredients:
		<ul>
			<c:forEach items="${allIngredients}" var="ingredient">
				<li>${ingredient.name}</li>
			</c:forEach>
		</ul>
	</div>
	<div style="clear:both;"></div>
<%@ include file="/WEB-INF/includes/footer.jspf" %>