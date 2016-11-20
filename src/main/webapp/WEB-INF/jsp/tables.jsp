<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Database meta inspector</title>
<link rel='stylesheet'href='/webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
<script type="text/javascript" src="/webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript"src="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
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
				<h1>Tables metadata for schema: ${schema}</h1>
			</div>
		</div>
	</div>

	<div class="container">
		<c:forEach items="${tables}" var="table" varStatus="status">
			<div class="col-xs-2">
				<table class="table table-striped" style="font-size: 9px;">
					<c:if test="${status.first}">
						<tr>
					</c:if>
					<th>${table.key}</th>

					<c:if test="${status.last}">
						</tr>
					</c:if>
					<c:forEach items="${table.value}" var="value">
						<tr>
							<td><c:choose>
									<c:when test="${table.key=='TABLE_NAME'}">
										<p>
											<a href="${schema}/${value}">${value}</a>
										</p>
									</c:when>
									<c:otherwise>
										<p>${value}</p>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:forEach>
	</div>
</body>
</html>