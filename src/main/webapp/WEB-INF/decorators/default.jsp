<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- http://static.springsource.org/spring/docs/3.2.x/spring-framework-reference/html/view.html --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec" %>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="author" content="Michael Lanyon">
	<title><dec:title default="spring-grabbag" /></title>
	<link href="<%= request.getContextPath() %>/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%= request.getContextPath() %>/static/css/main.css" rel="stylesheet">
	<dec:head />
</head>
<body>
	<div id="wrap">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<%= request.getContextPath() %>">Spring Grabbag</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li <%= request.getRequestURI().contains("cookbook") ? " class=\"active\"" : "" %>><a href="<%= request.getContextPath() %>/cookbook/">Cookbook</a></li>
						<li<%= request.getRequestURI().contains("d3") ? " class=\"active\"" : "" %>><a href="<%= request.getContextPath() %>/d3">D3 Experiment</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="main-content">
				<dec:body />
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="container">
			<p class="text-muted credit">&copy; ${year} Michael Lanyon</p>
		</div>
		<script src="//code.jquery.com/jquery.js"></script>
		<script src="<%= request.getContextPath() %>/static/js/bootstrap.min.js"></script>
		<dec:getProperty property="page.pageJS"/>
		<div class="analytics">
			<!-- guess what would go here... -->
		</div>
	</div>
</body>
</html>