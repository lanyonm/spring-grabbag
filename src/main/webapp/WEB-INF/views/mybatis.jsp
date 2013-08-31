<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>MyBatis Test</title>
</head>
<body>
	<h1>Mybatis</h1>
	<ul>
		<c:forEach items="${ingredients}" var="ingredient">
			<li>${ingredient.name} - <em>${ingredient.description}</em></li>
		</c:forEach>
	</ul>
</body>
</html>