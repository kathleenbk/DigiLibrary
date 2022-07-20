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
<title>Book</title>
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
	<div class="onebook">
		<div class="onebook-left">
			<img src="<c:url value="/images/newbook.png"/>" />
		</div>
		<div class="onebook-right">

			<div class="onebook-info">
				<h1 class="view-title">
					<c:out value="${ book.title }"></c:out>
				</h1>
				<h3>
					by
					<c:out value="${ book.author }"></c:out>
				</h3>
			</div>
			<!-- <h3>Genres:</h3> -->
			<div class="genre-list">
				<c:forEach var="genre" items="${ book.genres }">

					<p>
						<c:out value="${genre}"></c:out>
					</p>

				</c:forEach>
			</div>

			<div class="actions">
				<img class="heart-image" src="<c:url value="/images/heart.png"/>" />
				<c:out value="${fn:length(book.likes)}"></c:out>
				&nbsp &nbsp
				<c:choose>
					<c:when test="${ user.likes.contains(book) }">
						<a href="/book/${ book.id }/unlike" class="btn btn-dark">Unlike</a>
						<br />

					</c:when>
					<c:otherwise>
						<a href="/book/${ book.id }/like" class="btn btn-danger">Like</a>
						<br />

					</c:otherwise>
				</c:choose>
			</div>
			<c:choose>
				<c:when test="${ user.currentlyReading.id == book.id }">
					<div class="d-flex mt-4 current">
						<p>You are currently reading this book!</p>
						&nbsp &nbsp <a href="/book/${ book.id }/read">Remove</a>
					</div>
				</c:when>
				<c:otherwise>
					<a href="/book/${ book.id}/read" class="btn btn-danger mt-2">Set
						As Currently Reading</a>
				</c:otherwise>
			</c:choose>

			<br />
			<c:choose>
				<c:when test="${ user.futureReads.contains(book) }">
					<a href="/book/${ book.id }/future/remove"
						class="btn btn-dark mt-2">Remove from Future Reads</a>
				</c:when>
				<c:otherwise>
					<a href="/book/${ book.id }/future" class="btn btn-secondary mt-2">Add
						to Future Reads</a>
				</c:otherwise>
			</c:choose>
			&nbsp &nbsp
			<c:choose>
				<c:when test="${ user.pastReads.contains(book) }">
					<a href="/book/${ book.id }/past/remove" class="btn btn-dark mt-2">Remove
						from Past Reads</a>
				</c:when>
				<c:otherwise>
					<a href="/book/${ book.id }/past" class="btn btn-secondary mt-2">Add
						to Past Reads</a>
				</c:otherwise>
			</c:choose>
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