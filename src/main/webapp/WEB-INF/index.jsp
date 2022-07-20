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
	<div class="main-background1">
		<div class="form1"></div>

		<div class="form2">
			<h2 class="title2">Register</h2>
			<form:form action="/register" method="post" modelAttribute="newUser" class="form">
				<div class="form-row">
					<form:input id="firstName" path="firstName" class="form-control"
						placeholder="Name" />
					<form:errors path="firstName" />
				</div>
				<div class="form-row">
					<form:input id="username" path="username" class="form-control"
						placeholder="Username" />
					<form:errors path="username" />
				</div>
				<div class="form-row">
					<form:input path="email" class="form-control" placeholder="Email" />
					<form:errors path="email" />
				</div>
				<div class="form-row">
					<form:input path="password" class="form-control" type="password"
						placeholder="Password" />
					<form:errors path="password" />
				</div>
				<div class="form-row">
					<form:input path="confirm" class="form-control" type="password"
						placeholder="Confirm Password" />
					<form:errors path="confirm" />
				</div>
				<input type="submit" value="Submit" class="btn mt-3 btn-light shadow2" />
			</form:form>
		</div>
		<div class="form3"></div>
		<div class="form4"></div>
		<div class="form5">
			<h2 class="title2">Log in</h2>
			<form:form action="/login" method="post" modelAttribute="newLogin" class="form">
				<div class="form-row">
					<form:input path="username" class="form-control black"
						placeholder="Username" />
					<form:errors path="username" class="text-danger" />
				</div>
				<div class="form-row">
					<form:input path="password" class="form-control black"
						type="password" placeholder="Password" />
					<form:errors path="password" class="text-danger" />
				</div>
				<input type="submit" value="Submit" class="btn mt-3 btn-light shadow2" />
			</form:form>

		</div>
		<div class="form6"></div>
	</div>
	<div class="navbar">
	<div class="nav-left">
		<a href="/register">Log In / Register</a></div><div class="nav-right"><a href="/">Home</a><a
			href="/about">About</a></div>
	</div>
</body>
</html>