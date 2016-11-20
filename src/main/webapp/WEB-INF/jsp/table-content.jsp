<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Database meta inspector</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel='stylesheet'
	href='/webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
<script type="text/javascript" src="/webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript"
	src="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<img style="width: 40px; height: 47px;"
					src="/resources/images/icon.png">
				<a href="/" class="navbar-brand">Database explorer application</a>
			</div>
		</div>
	</nav>
	
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center" style="margin-bottom: 100px;">
				<h1>Content of table: ${table}, schema: ${schema}</h1>
			</div>
		</div>
	</div>

	<div class="table-responsive">
		<c:forEach items="${columnNameToValuesMap}" var="mapItem">
			<div class="col-xs-3">
				<table class="table table-striped" style="font-size: 9px;">
					<tr>
						<th>${mapItem.key}</th>
					</tr>
					<c:forEach items="${mapItem.value}" var="value">
						<tr>
							<td><p>${value}</p></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:forEach>
	</div>
</body>
</html>