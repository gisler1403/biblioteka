<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form:form action="updateBook" method="post" modelAttribute="bookToUpdate">
	<form:hidden path="id"/>
	<form:hidden path="authorId"/>
	Title : <form:input path="title" />
		<br>
	ISBN Number : <form:input path="isbnNumber" />
		<br>
	Publication Date : <form:input path="publicationDate" />
		<br>
	Author's first name : <form:input path="firstNameAuthor" />
		<br>
	Author's last name : <form:input path="lastNameAuthor" />
		<br>

		<input type="submit" value="Update Book">

	</form:form>
</body>
</html>