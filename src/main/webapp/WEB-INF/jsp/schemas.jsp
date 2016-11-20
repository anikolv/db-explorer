<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Database meta inspector</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel='stylesheet'href='/webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
<script type="text/javascript" src="/webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript"src="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<img style="width: 40px; height: 47px;"
					src="http://megaicons.net/static/img/icons_sizes/8/178/512/data-add-database-icon.png">
				<a href="#" class="navbar-brand">Database explorer application</a>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h1>Database server schemas:</h1>
				<ul class="list-unstyled">
					<c:forEach items="${schemas}" var="schema">
	    				<li><p class="lead"><a class="btn btn-primary" style="width: 500px;" href="${schema}">${schema}</a></p></li><br>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>

