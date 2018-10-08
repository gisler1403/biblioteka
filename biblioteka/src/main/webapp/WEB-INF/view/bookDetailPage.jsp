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
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${foundBook}" var="book">
	<p>Title : ${book.title}</p>
	<p>ISBN : ${book.isbnNumber}</p>
	<p> Author : ${book.author.firstName} ${book.author.lastName}</p>
	<p>Publication Date : ${book.publicationDateString}</p>
	-----------------------------------------------------------------------
	</c:forEach>
</body>
</html>