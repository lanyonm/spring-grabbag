<%@ include file="/WEB-INF/includes/header.jspf" %>
	<title>DB</title>
</head>
<body>
	<h1>${ ingredient.id == 0 ? 'Add' : 'Edit' } Ingredient</h1>
	<form:form commandName="ingredient">
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
              <td colspan="2">
                  <input type="submit" value="Save" />
              </td>
          </tr>
      </table>
	</form:form>
<%@ include file="/WEB-INF/includes/footer.jspf" %>