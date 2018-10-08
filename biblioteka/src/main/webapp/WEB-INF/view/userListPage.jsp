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
	<table>
		<tr>
			<th>Username</th>
			<th>Email</th>
			<th>Action</th>

		</tr>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.username}</td>
				<td>${user.email}</td>
				<td><c:url var="userDetails" value="/userAdmin/userDetails">
					<c:param name="userId" value="${user.id}"/></c:url><a href="${userDetails}">User Details</a></td>
			</tr>

		</c:forEach>


	</table>

</body>
</html>