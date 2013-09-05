<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<spring:message code="ingredient.name" var="name" />
	<title>${ isNew ? 'Add Ingredient' : 'Edit ' }${ingredient.name}</title>
</head>
<body>
	<h1>${ isNew ? 'Add Ingredient' : 'Edit ' }${ingredient.name}</h1>
	<form:form commandName="ingredient">
		<table>
			<tr>
				<td>${name}:</td>
				<td><form:input path="name" /> <span style="color:red;"><form:errors path="name" /></span></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><form:input path="description" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
