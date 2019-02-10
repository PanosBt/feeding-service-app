	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<h4>Τροποποίηση στοιχείων χρήστη</h4>
	<br />
	
	<form:form method="POST"
		action="${pageContext.request.contextPath}/admin/modify_user/${user.username}"
		modelAttribute="user_modified">
		<table border="1">
			<tr>
				<td>Τύπος Χρήστη</td>
				<td>${role}</td>
			</tr>
			<tr>
				<td>Username</td>
				<td>${user.username}</td>
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
				<td><fmt:formatDate value="${user.dateOfBirth}" pattern="dd-MM-yyyy" /></td>
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
					<td><form:select path="dept">
							<form:option value="DIT">Πληροφορικής και Τηλεματικής</form:option>
							<form:option value="GEO">Γεωγραφίας</form:option>
							<form:option value="DHEE">Οικιακής Οικονομίας</form:option>
							<form:option value="DDNS">Επιστήμης Διαιτολογίας – Διατροφής</form:option>
						</form:select>
					</td>
					<!-- <td><form:input path="dept" /></td>-->
				</tr>
			</c:if>

		</table>
		<br />
		<input type="submit" value="ΑΠΟΘΗΚΕΥΣΗ"/>
	</form:form>
	<c:if test="${not empty userUpdated}">
		<c:choose>
			<c:when test="${userUpdated}">
				<div>Ο χρήστης ενημερώθηκε!</div>
			</c:when>
			<c:otherwise>
				<div class="error">Η ενημέρωση απέτυχε</div>
			</c:otherwise>
		</c:choose>
	</c:if>
