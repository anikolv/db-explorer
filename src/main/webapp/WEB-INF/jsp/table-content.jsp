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

	<p>Content of table ${schema}</p>
	<div style="white-space: nowrap;">
		<c:forEach items="${columnNameToValuesMap}" var="mapItem">
			<table style="display: inline-block;">
				<tr>
					<th>${mapItem.key}</th>
				</tr>
				<c:forEach items="${mapItem.value}" var="value">
					<tr>
						<td><p>${value}</p></td>
					</tr>
				</c:forEach>
			</table>
		</c:forEach>
	</div>
</body>
</html>