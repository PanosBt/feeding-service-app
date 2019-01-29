<%-- list of student usernames that have not a card --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<table border = 1 >
			<tr>
				<th>Username</th>
			</tr>
			<!-- loop over and print our customers -->
			<c:forEach var="tempStudent" items="${students}">
				<tr>
					<td>${tempStudent.username}</td>
					<td><button type="submit" name="username" value=${tempStudent.username}  class="ui button">Button</button></td>
				</tr>
			</c:forEach>
		</table>
		</form>
</body>
</html>