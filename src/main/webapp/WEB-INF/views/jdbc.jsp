<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>JDBC</title>
</head>
<body>
	<h1>JDBC</h1>
	<ul>
		<c:forEach items="${users}" var="user">
			<li>${user.firstName} ${user.lastName}</li>
		</c:forEach>
	</ul>
</body>
</html>