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
	Welcome 
	
	<security:authorize access="hasRole('ADMIN')">
	<p>
		<a href="${pageContext.request.contextPath}/bookAdmin/goAddBook">Add Book</a>
	</p></security:authorize>
	
	
	<security:authorize access="hasRole('USER')">
	<p>
		<a href="${pageContext.request.contextPath}/bookUser/listBooks">List Book</a>
	</p>
	</security:authorize>
	<security:authorize access="hasRole('ADMIN')">
	<p>
		<a href="${pageContext.request.contextPath}/userAdmin/userList">List of User</a>
	</p>
	</security:authorize>
	<p>
		Szukaj : <form:form action="${pageContext.request.contextPath}/bookUser/search" method="post" modelAttribute="searchedBook" >
				<form:input path="searchedName"/>
				<form:select path="category">
					<form:options items="${searchedBook.categoryList}"/>
				</form:select>
			
				<input type="submit" value="Szukaj">
				
				</form:form>
	</p>
	<security:authorize access="isAuthenticated()">
	<form:form action="${pageContext.request.contextPath}/logout"
		method="post">
		<p>
			<input type="submit" value="Logout" />
		</p>
	</form:form>
	</security:authorize>
	<security:authorize access="isAnonymous()">
		<p><a href="${pageContext.request.contextPath}/customLogin">Log in</a></p>
	</security:authorize>
	
	<p><a href="${pageContext.request.contextPath}/userAdmin/goRegisterUser">Register</a></p>
</body>
</html>