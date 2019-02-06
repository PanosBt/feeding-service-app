<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="POST"
		action="${pageContext.request.contextPath}/clerk/applicationinfo">
		<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
		<table border=1>
			<tr>
				<th>ID Αίτησης</th>
			</tr>
			<c:forEach var="application" items="${applications}">
				<tr>
					<td>${application.appl_id}</td>
					<td><button type="submit" name="appl_id"
							value=${application.appl_id }  class="ui button">Επεξεργασία</button></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>