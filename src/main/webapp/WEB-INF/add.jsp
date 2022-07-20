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
<title>Add a Book</title>
</head>
<body class="body">
	<div class="welcome">
		<h2 class="title3"><a class="title3" href="/home">DigiLibrary</a></h2>
		<div class="search-bar">

			<form action="/search">
				<input type="text" class="search-input" placeholder="Search by Title, Author, or Genre"
					name="searchString" />
					<input type="submit" class="btn btn-light" value="Search" />
			</form>

		</div>
	</div>
	<div class="d-flex justify-content-between p-5">
		<div>
			<img src="<c:url value="/images/newbook.png"/>" />
		</div>
		<div class="newform">
			<form:form action="/add" method="post" modelAttribute="book">
				<form:errors path="title" class="text-danger" />
				<form:errors path="author" class="text-danger" />
				<form:errors path="genres" class="text-danger" />
				<form:label class="mt-2" path="title">
					<h4>Title</h4>
				</form:label>
				<form:input path="title" class="form-control" />
				<form:label class="mt-2" path="author">
					<h4>Author</h4>
				</form:label>
				<form:input path="author" class="form-control" />
				<form:label class="mt-2" path="genres">
					<h4>Genre(s) - Please select all that apply</h4>
				</form:label>
				<div class="genres p-2">
					<h5>Fiction</h5>

					<table class="table1">
						<tbody>
							<tr>
								<td><form:checkbox path="genres" value="Fantasy" />Fantasy</td>
								<td><form:checkbox path="genres" value="Science Fiction" />Sci-fi</td>
								<td><form:checkbox path="genres" value="Dystopian" />Dystopian</td>
								<td><form:checkbox path="genres" value="Adventure" />Adventure</td>
							</tr>
							<tr>
								<td><form:checkbox path="genres" value="Romance" />Romance</td>
								<td><form:checkbox path="genres" value="Mystery" />Mystery</td>
								<td><form:checkbox path="genres" value="Horror" />Horror</td>
								<td><form:checkbox path="genres" value="Thriller" />Thriller</td>
							</tr>
							<tr>
								<td><form:checkbox path="genres" value="LGBTQ+" />LGBTQ+</td>
								<td><form:checkbox path="genres" value="Historical" />Historical</td>
								<td><form:checkbox path="genres" value="Young Adult" />Young
									Adult</td>
								<td><form:checkbox path="genres" value="Children's" />Children's</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="genres mt-3 p-2">
					<h5>Non-Fiction</h5>

					<table class="table1">
						<tbody>
							<tr>
								<td><form:checkbox path="genres"
										value="Memoir & Autobiography" />Memoir & Autobiography</td>
								<td><form:checkbox path="genres" value="Biography" />Biography</td>
								<td><form:checkbox path="genres" value="Art & Photography" />Art
									& Photography</td>
								<td><form:checkbox path="genres" value="Cooking" />Cooking</td>
							</tr>
							<tr>
								<td><form:checkbox path="genres"
										value="Personal Development" />Personal Development</td>
								<td><form:checkbox path="genres" value="Motivational" />Motivational</td>
								<td><form:checkbox path="genres" value="Health" />Health</td>
								<td><form:checkbox path="genres" value="History" />History</td>
							</tr>
							<tr>
								<td><form:checkbox path="genres" value="Hobbies" />Hobbies</td>
								<td><form:checkbox path="genres" value="Relationships" />Relationships</td>
								<td><form:checkbox path="genres" value="Entertainment" />Entertainment</td>
								<td><form:checkbox path="genres" value="Business" />Business</td>
							</tr>
							<tr>
								<td><form:checkbox path="genres" value="Hobbies" />Hobbies</td>
								<td><form:checkbox path="genres" value="Law" />Law</td>
								<td><form:checkbox path="genres" value="Politics" />Politics</td>
								<td><form:checkbox path="genres"
										value="Religion & Spirituality" />Religion & Spirituality</td>
							</tr>
							<tr>
								<td><form:checkbox path="genres" value="Education" />Education</td>
								<td><form:checkbox path="genres" value="True Crime" />True
									Crime</td>
								<td><form:checkbox path="genres" value="Travel" />Travel</td>
								<td><input type="checkbox"  /><form:input path="genres" placeholder="Other Genre" /></td>

							</tr>
						</tbody>
					</table>
				</div>
				


				<input type="submit" class="btn btn-success mt-3" />
			</form:form>
		</div>
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