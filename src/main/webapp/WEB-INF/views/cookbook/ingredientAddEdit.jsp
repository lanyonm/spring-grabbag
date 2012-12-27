<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
</body>
</html>