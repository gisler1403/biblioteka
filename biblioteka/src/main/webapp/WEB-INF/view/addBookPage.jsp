<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<form:form action="addBook" method="post" modelAttribute="newBook">
	Title : <form:input path="title"/> <form:errors path="title"/> <br>
	ISBN Number : <form:input path="isbnNumber"/> <form:errors path="isbnNumber"/><br>
	Publication Date : <form:input path="publicationDate"/> <form:errors path="publicationDate"/><br>
	Author's first name : <form:input path="firstNameAuthor"/> <form:errors path="firstNameAuthor"/><br>
	Author's last name : <form:input path="lastNameAuthor"/> <form:errors path="lastNameAuthor"/><br>
	<input type="submit" value="Add Book">
	
	</form:form>


</body>
</html>