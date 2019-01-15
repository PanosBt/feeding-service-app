<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="welcome-admin-text">
		<h4>Καλώς ήρθατε ${firstName} ${lastname}!</h4>
		<br />
	</div>

	<div class="admin-functions">
		<h4>Επιλογές Διαχειριστή</h4>
		<br />
		<br /> 
		Δημιουργία νέου χρήστη
		<br />
		<br />
		<form:form method="POST" action="admin/create_user" modelAttribute="user">
			<tr>
				<td><form:label path="username">username</form:label></td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td><form:label path="password">password</form:label></td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td><form:label path="userType">user type</form:label></td>
				<td>
					<form:select path="userType">
						<form:option value="student">Student</form:option>
						<form:option value="clerk">Clerk</form:option>
						<form:option value="supervisor">Supervisor</form:option>
					</form:select>
				</td>
				<td><input type="submit" value="CREATE"/></td>
			</tr>

		</form:form>

	</div>
</body>
</html>