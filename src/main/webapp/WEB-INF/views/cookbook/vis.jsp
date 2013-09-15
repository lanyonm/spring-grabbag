<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<title>Cookbook Visualization</title>
</head>
<body>
	<h1>Cookbook Graph</h1>
	<div class="stats">
		<p>${fn:length(recipes)} Recipes</p>
		<p>${fn:length(ingredients)} Ingredients</p>
	</div>
	<div id="d3div"></div>
</body>
</html>
<content tag="pageJS">
	<script src="http://d3js.org/d3.v3.min.js" charset="UTF-8"></script>
	<script src="<%= request.getContextPath() %>/static/js/cookbook-vis.js"></script>
</content>