<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form method="POST"
		action="${pageContext.request.contextPath}/admin/modify_user"
		modelAttribute="user_modified">
		<table border="1">
			<tr>
				<td>Τύπος Χρήστη</td>
				<td>${role}</td>
			</tr>
			<tr>
				<td><form:label path="firstName">Όνομα</form:label></td>
				<td><c:out value="${user.firstName}" /></td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Επώνυμο</form:label></td>
				<td><c:out value="${user.lastName}" /></td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td><form:label path="dateOfBirth">Ημ/νία Γέννησης</form:label></td>
				<td><c:out value="${user.dateOfBirth}" /></td>
				<td><form:input type="date" path="dateOfBirth" /></td>
			</tr>
			<tr>
				<td><form:label path="identityCardNO">Αριθμός Ταυτότητας</form:label></td>
				<td><c:out value="${user.identityCardNO}" /></td>
				<td><form:input path="identityCardNO" /></td>
			</tr>
			<tr>
				<td><form:label path="email">email</form:label></td>
				<td><c:out value="${user.email}" /></td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td><form:label path="phone">Τηλέφωνο</form:label></td>
				<td><c:out value="${user.phone}" /></td>
				<td><form:input path="phone" /></td>
			</tr>
			<c:if test="${role == 'Clerk'}">
				<tr>
					<td><form:label path="supervising_dept">Τμήμα Επίβλεψης</form:label></td>
					<td><c:out value="${user.supervising_dept}" /></td>
					<td><form:input path="supervising_dept" /></td>
				</tr>
			</c:if>
			<c:if test="${role == 'Student'}">
				<tr>
					<td><form:label path="academicID">Αριθμός Ακαδημαϊκής Ταυτότητας</form:label></td>
					<td><c:out value="${user.academicID}" /></td>
					<td><form:input path="academicID" /></td>
				</tr>
				<tr>
					<td><form:label path="dept">Τμήμα</form:label></td>
					<td><c:out value="${user.dept}" /></td>
					<td><form:input path="dept" /></td>
				</tr>
			</c:if>

		</table>
		<br />
		<input type="submit" value="ΑΠΟΘΗΚΕΥΣΗ"/>
	</form:form>

</body>
</html>