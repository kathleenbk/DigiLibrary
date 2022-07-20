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
<title><c:out value="${ user2.username }"></c:out></title>
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
	<div class="p-3">
		<div class="profile-info-container">
			<div class="profile-info">
				<h1 class="shadow1">
					<c:out value="${ user2.firstName}"></c:out>
					's Profile
				</h1>
				<c:choose>
					<c:when test="${ user.following.contains(user2) }">
						<a href="/unfollow/${ user2.id }" class="btn btn-dark">Unfollow</a>
					</c:when>
					<c:otherwise>
						<a href="/follow/${ user2.id }" class="btn btn-dark">Follow</a>

					</c:otherwise>
				</c:choose>
				<h4 class="shadow1">
					Currently Reading: <a href="/books/${ user2.currentlyReading.id }">
						<div class="flip-card">
							<div class="book flip-card-inner">
								<div class="flip-card-front">
									<h4>
										<c:out value="${ user2.currentlyReading.title }"></c:out>
									</h4>
									<p class="author1">
										<c:out value="${ user2.currentlyReading.author }"></c:out>
									</p>
								</div>
								<div class="flip-card-back">
									<div class="main-info">
										<p class="book-title">
											<c:out value="${ user2.currentlyReading.title }"></c:out>
										</p>
									</div>
									<div class="other-info">

										<c:forEach var="genre"
											items="${ user2.currentlyReading.genres }">
											<p class="p">
												<c:out value="${genre}">, </c:out>
											</p>
										</c:forEach>

										<img class="heart-image"
											src="<c:url value="/images/heart.png"/>" />
										<c:out value="${fn:length(user2.currentlyReading.likes)}"></c:out>
									</div>
								</div>
							</div>
						</div>
					</a>
				</h4>
			</div>
			<div class="friend-container">
				<h1 class="shadow1">
					<c:out value="${ user2.firstName }"></c:out>
					's Followers
				</h1>
				<c:forEach var="follower" items="${ user2.followers }">
					<a href="/user/${ follower.id }" class="userlink">@<c:out
							value="${ follower.username }"></c:out></a>
				</c:forEach>
			</div>
		</div>
		<p class="header">Future Reads ></p>
		<div class="browse mt-2">
			<c:forEach var="book" items="${ user2.futureReads }">
				<a href="/books/${ book.id }">
					<div class="flip-card">
						<div class="book flip-card-inner">
							<div class="flip-card-front">
								<h4>
									<c:out value="${ book.title }"></c:out>
								</h4>
								<p class="author1">
									<c:out value="${ book.author }"></c:out>
								</p>
							</div>
							<div class="flip-card-back">
								<div class="main-info">
									<p class="book-title">
										<c:out value="${ book.title }"></c:out>
									</p>
								</div>
								<div class="other-info">

									<c:forEach var="genre" items="${ book.genres }">
										<p class="p">
											<c:out value="${genre}">, </c:out>
										</p>
									</c:forEach>

									<img class="heart-image"
										src="<c:url value="/images/heart.png"/>" />
									<c:out value="${fn:length(book.likes)}"></c:out>
								</div>
							</div>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>


		<p class="header">Likes ></p>
		<div class="browse mt-2">
			<c:forEach var="book" items="${ user2.likes }">
				<a href="/books/${ book.id }">
					<div class="flip-card">
						<div class="book flip-card-inner">
							<div class="flip-card-front">
								<h4>
									<c:out value="${ book.title }"></c:out>
								</h4>
								<p class="author1">
									<c:out value="${ book.author }"></c:out>
								</p>
							</div>
							<div class="flip-card-back">
								<div class="main-info">
									<p class="book-title">
										<c:out value="${ book.title }"></c:out>
									</p>
								</div>
								<div class="other-info">

									<c:forEach var="genre" items="${ book.genres }">
										<p class="p">
											<c:out value="${genre}">, </c:out>
										</p>
									</c:forEach>

									<img class="heart-image"
										src="<c:url value="/images/heart.png"/>" />
									<c:out value="${fn:length(book.likes)}"></c:out>
								</div>
							</div>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
		<p class="header">Past Reads ></p>
		<div class="browse mt-2">
			<c:forEach var="book" items="${ user2.pastReads }">
				<a href="/books/${ book.id }">
					<div class="flip-card">
						<div class="book flip-card-inner">
							<div class="flip-card-front">
								<h4>
									<c:out value="${ book.title }"></c:out>
								</h4>
								<p class="author1">
									<c:out value="${ book.author }"></c:out>
								</p>
							</div>
							<div class="flip-card-back">
								<div class="main-info">
									<p class="book-title">
										<c:out value="${ book.title }"></c:out>
									</p>
								</div>
								<div class="other-info">

									<c:forEach var="genre" items="${ book.genres }">
										<p class="p">
											<c:out value="${genre}">, </c:out>
										</p>
									</c:forEach>

									<img class="heart-image"
										src="<c:url value="/images/heart.png"/>" />
									<c:out value="${fn:length(book.likes)}"></c:out>
								</div>
							</div>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
		<div class="browse mt-4"></div>
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