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

	<form:form action="${pageContext.request.contextPath}/authenticationForm" modelAttribute="user" method="post">
	
	<c:if test="${param.error != null}">
		<i>Wrong login or password</i>	
	</c:if>
	
	<c:if test="${param.logout != null}">
		<i>Successfully logout</i>
	</c:if>
	<p>
	Login : <input type="text" name="username"  /> <br>
	</p>
	<p>
	Password : <input type="password" name="password"/> <br>
	</p>
	<p>
	<input type="submit" value="Login"/>
	</p>
	
	</form:form>
	
	
</body>
</html>