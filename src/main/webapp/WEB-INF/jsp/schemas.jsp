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

	<p>Local MySQL schemas:</p>
	<c:forEach items="${schemas}" var="schema">
    		<a href="mysql/schema/${schema}">${schema}</a><br>
	</c:forEach>
</body>
</html>