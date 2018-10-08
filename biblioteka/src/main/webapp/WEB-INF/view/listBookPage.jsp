<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BookShelf</title>
</head>
<body>
	
	<table>
		<tr>
			<th>Title</th>
			<th>ISBN</th>
			<th>Author</th>
			<th>Date of Publication</th>
			
		</tr>
		<c:forEach var="book" items="${listOfBooks}">
			<tr>
				<td>${book.title}</td>
				<td>${book.isbnNumber}</td>
				<td><c:url var="authorDetail" value="/bookAdmin/authorDetail">
					<c:param name="authorId" value="${book.author.id}"/>
				</c:url>
				<a href="${authorDetail}">		
				${book.author.firstName} ${book.author.lastName}</a></td>
				<td>${book.publicationDate}</td>
				<td><security:authorize access="hasRole('ADMIN')">
						<c:url var="updateBook" value="/bookAdmin/goUpdateBook">
							<c:param name="bookId" value="${book.id}"/>
						</c:url>
						<a href="${updateBook}">Update</a>
					</security:authorize></td>
				<td><security:authorize access="hasRole('ADMIN')">
						<c:url var="deleteBook" value="/bookAdmin/deleteBook">
							<c:param name="bookId" value="${book.id}"/>
						</c:url>
						<a href="${deleteBook}"
							onclick="if (!(confirm('Are you sure you want to delete this book?'))) return false">Delete</a>
					</security:authorize></td>
				<td><c:if test="${book.reservationUser == null}">
						<c:if test="${book.borrowingUser == null}">
							<c:url var="bookReservation" value="/bookUser/bookReservation">
								<c:param name="bookId" value="${book.id}"></c:param>
							</c:url>
							<a href="${bookReservation}">Zarezerwuj</a>
						</c:if>
					</c:if> <c:if test="${book.reservationUser  != null}"> ${book.reservationUser.username}</c:if>
					<security:authorize access="hasRole('ADMIN')">
						<c:if test="${book.reservationUser != null}">
							<c:url var="lendBook" value="/bookAdmin/lendBook">
								<c:param name="bookId" value="${book.id}"></c:param>
							</c:url>
							<a href="${lendBook}">Wypożycz</a>
						</c:if>
					</security:authorize> 
					<security:authorize access="hasRole('ADMIN')">
						<c:if test="${book.borrowingUser != null}">Wypożyczona przez ${book.borrowingUser.username}</c:if>
					</security:authorize></td>
					<security:authorize access="hasRole('ADMIN')"><td>
						<c:if test="${book.borrowingUser != null}">
							<c:url var="giveBackBook" value="/bookAdmin/giveBackBook">
							<c:param name="bookId" value="${book.id}"/></c:url>
							<a href="${giveBackBook}">Zwróć książkę</a>
						</c:if>
					
					</td></security:authorize>
			</tr>

		</c:forEach>


	</table>

</body>
</html>