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
	
		<h4>Τροποποίηση στοιχείων χρήστη</h4>
		<form:form method="POST"
			action="${pageContext.request.contextPath}/clerk/modify_student/${student.username}"
			modelAttribute="mod_student">
			<table border="1">
				<tr>
					<td>Username</td>
					<td>${student.username}</td>
				</tr>
				<tr>
					<td><form:label path="firstName">Όνομα</form:label></td>
					<td><c:out value="${student.firstName}" /></td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td><form:label path="lastName">Επώνυμο</form:label></td>
					<td><c:out value="${student.lastName}" /></td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td><form:label path="dateOfBirth">Ημ/νία Γέννησης</form:label></td>
					<td><fmt:formatDate value="${student.dateOfBirth}"
							pattern="dd-MM-yyyy" /></td>
					<td><form:input type="date" path="dateOfBirth" /></td>
				</tr>
				<tr>
					<td><form:label path="identityCardNO">Αριθμός Ταυτότητας</form:label></td>
					<td><c:out value="${student.identityCardNO}" /></td>
					<td><form:input path="identityCardNO" /></td>
				</tr>
				<tr>
					<td><form:label path="email">email</form:label></td>
					<td><c:out value="${student.email}" /></td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td><form:label path="phone">Τηλέφωνο</form:label></td>
					<td><c:out value="${student.phone}" /></td>
					<td><form:input path="phone" /></td>
				</tr>
					<tr>
						<td><form:label path="academicID">Αριθμός Ακαδημαϊκής Ταυτότητας</form:label></td>
						<td><c:out value="${student.academicID}" /></td>
						<td><form:input path="academicID" /></td>
					</tr>
					<tr>
						<td><form:label path="dept">Τμήμα</form:label></td>
						<td><c:out value="${student.dept}" /></td>
						<td><form:input path="dept" /></td>
					</tr>
			</table>
			<br />
			<input type="submit" value="ΑΠΟΘΗΚΕΥΣΗ" />
		</form:form>
		<form action="${pageContext.request.contextPath}/clerk/studentlist" method="get">
			<button type="submit">Πίσω</button><br>
		</form>
		<c:if test="${not empty studentUpdated}">
			<c:choose>
				<c:when test="${studentUpdated}">
					<div>Η καρτέλα ενημερώθηκε επιτυχώς!</div>
				</c:when>
				<c:otherwise>
					<div class="error">Η ενημέρωση απέτυχε</div>
				</c:otherwise>
			</c:choose>
		</c:if>
</body>
</html>