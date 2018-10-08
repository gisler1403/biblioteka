<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
	<p>Author names : ${authorDetail.firstName} ${authorDetail.lastName}</p>
	<p>Books:</p>
	<table>
		<tr>
			<th>Title</th>
			<th>ISBN</th>
			<th>Date of Publication</th>
		</tr>
		<c:forEach var="authorBooks" items="${authorDetail.bookList}">
			<tr>
				<td>${authorBooks.title}</td>
				<td>${authorBooks.isbnNumber}</td>
				<td>${authorBooks.publicationDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>