<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Mysql database explorer</title>
</head>
<body>

	<p>Tables:</p>
	<c:forEach items="${tables}" var="table">
    		<a href="mysql/schema/${table}">${table}</a><br>
	</c:forEach>
</body>
</html>