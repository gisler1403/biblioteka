<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<form:form action="${pageContext.request.contextPath}/userAdmin/registerUser" modelAttribute="newUser" method="post">
	
	<p>
	Username <form:input path="username"/> <form:errors path="username"/>
	</p>
	
	<p>
	Password<form:input path="password" type="password"/> <form:errors path="password"/>
	</p>
	
	<p>
	Email <form:input path="email"/> <form:errors path="email"/>
	</p>	
	
	<p>
		<input type="submit" value="Register"/>
	</p>


</form:form>

</body>
</html>