<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>Username : ${userDetail.username}</p>
	<p>Email : ${userDetail.email}</p>
	<p>Borrowed books :</p>
	<table>
		<tr>
			<th>Title</th>
			<th>ISBN</th>
			<th>Author</th>
			<th>Date of Publication</th>
		</tr>
		<c:forEach var="userBooks" items="${userDetail.borrowedBooks}">
			<tr>
				<td>${userBooks.title}</td>
				<td>${userBooks.isbnNumber}</td>
				<td>${userBooks.author.firstName}${userBooks.author.lastName}</td>
				<td>${userBooks.publicationDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>