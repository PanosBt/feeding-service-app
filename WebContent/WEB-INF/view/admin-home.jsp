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

		<h5>Δημιουργία νέου χρήστη</h5> 
		<div class="admin-create-form">
			<form:form method="POST" action="admin/create_user"
				modelAttribute="user">
				<table>
					<tr>
						<td><form:label path="username">Όνομα χρήστη</form:label></td>
						<td><form:input path="username" /></td>
					</tr>
					<tr>
						<td><form:label path="password">Κωδικός</form:label></td>
						<td><form:password path="password" /></td>
					</tr>
					<tr>
						<td><form:label path="userType">Τύπος χρήστη</form:label></td>
						<td><form:select path="userType">
								<form:option value="student">Student</form:option>
								<form:option value="clerk">Clerk</form:option>
								<form:option value="supervisor">Supervisor</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td><input type="submit" value="ΔΗΜΙΟΥΡΓΙΑ" /></td>
					</tr>
				</table>
	
			</form:form>
		</div>
		<br />
		<hr>
		<h5>Τροποποίηση Στοιχείων Χρήστη</h5>

		<div class="admin-search-user-for-mod">
			<form method="POST" action="admin/modify_user">
				<table>
					<tr>
						<td>Όνομα χρήστη</td>
						<td><input type="text" name="username"></td>
					</tr>
					<tr>
						<td><input type="submit" value="ΑΝΑΖΗΤΗΣΗ"></td>
					</tr>
				</table>
			</form>
		</div>
		<br />		
		<hr>
		<h5>Διαγραφή Χρήση</h5>
		<div class="admin-delete-user">
			<form method="POST" action="admin/delete_user">
				<table>
					<tr>
						<td>Όνομα χρήστη</td>
						<td><input type="text" name="username"></td>
					</tr>
					<tr>
						<td><input type="submit" value="ΔΙΑΓΡΑΦΗ"></td>
					</tr>
				</table>
			</form>
		</div>

	</div>
</body>
</html>