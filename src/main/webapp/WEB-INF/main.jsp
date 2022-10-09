<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>


<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Libre+Baskerville:wght@700&display=swap"
	rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Read a book.</title>
</head>
<body>
<div class="main-background">
		<div class="book-image">
		<img src="<c:url value="/images/bookimg.png"/>" />
		</div>
		<div class="main-text">
			<h1>Read a book.</h1>
		</div>
	</div>
	<div class="navbar">
	<a href="/login">Log In</a>
	<a href="/register">Register</a>
	<a href="/about">About</a>
	</div>
</body>
</html>