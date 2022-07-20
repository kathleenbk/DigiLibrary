<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
<title>About DigiLibrary</title>
</head>
<body class="body">
	<div class="welcome">

		<h2 class="title3">
			<a class="title3" href="/home">DigiLibrary</a>
		</h2>
		<div class="search-bar">

			<form action="/search">
				<input type="text" class="search-input"
					placeholder="Search by Title, Author, or Genre" name="searchString" />
				<input type="submit" class="btn btn-light" value="Search" />
			</form>

		</div>
	</div>
	<div class="p-4">
		<h3>This website was designed as a first solo Java project by
			Kathleen Kavanagh.</h3>
		<br />
		<h3>
			Any feedback is appreciated. <a
				href="mailto: kavanagh.kathleenb@gmail.com">Send Feedback</a>
		</h3>
	</div>
	<div class="navbar">
		<div class="nav-left">
			<a href="/profile">Profile</a>
		</div>
		<div class="nav-right">
			<a href="/logout">Sign Out</a><a href="/about">About</a>
		</div>
	</div>
</body>
</html>
