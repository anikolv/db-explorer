<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Mysql database schema explorer</title>
</head>
<body>

	<p>Tables metadata</p>
	<div style="white-space: nowrap;">
		<c:forEach items="${tables}" var="table">
			<table style="display: inline-block;">
				<tr>
					<th>${table.key}</th>
				</tr>
				<c:forEach items="${table.value}" var="value">
					<tr>
						<td>
							<c:choose>
								<c:when test="${table.key=='TABLE_NAME'}">
									<p><a href="/mysql/schema/${schema}/table/${value}">${value}</a></p>
								</c:when>
								<c:otherwise>
									<p>${value}</p>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:forEach>
	</div>
</body>
</html>