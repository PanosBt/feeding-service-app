<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Student List</title>
</head>
<body>

	<form method="POST"
		action="${pageContext.request.contextPath}/clerk/modify_student_search">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<table border=1>
			<tr>
				<th>Username</th>
			</tr>
			<!-- loop over and print our customers -->
			<c:forEach var="tempStudent" items="${students}">
				<tr>
					<td>${tempStudent.username}</td>
					<td><button type="submit" name="username"
							value=${tempStudent.username }  class="ui button">Επεξεργασία</button></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<form action="${pageContext.request.contextPath}/clerk"
		method="get">
		<button type="submit">Πίσω</button>
		<br>
	</form>
</body>
</html>