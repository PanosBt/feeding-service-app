	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link href="<c:url value="/resources/css/simple-table.css"/>" rel="stylesheet" type="text/css">
	<div class="rounded-box">
	<div class="center">
	<h4>Τροποποίηση στοιχείων χρήστη</h4>
	<br />
	
	<form:form method="POST"
		action="${pageContext.request.contextPath}/admin/modify_user/${user.username}"
		modelAttribute="user_modified">
		<table class="simple-table" style="border: 1px solid #d3e4ec;">
			<tr>
				<td>Τύπος Χρήστη</td>
				<td>${role}</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>Username</td>
				<td>${user.username}</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><form:label path="firstName">Όνομα</form:label></td>
				<td><c:out value="${user.firstName}" /></td>
				<td><form:input class="text-input" path="firstName" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Επώνυμο</form:label></td>
				<td><c:out value="${user.lastName}" /></td>
				<td><form:input class="text-input" path="lastName" /></td>
			</tr>
			<tr>
				<td><form:label path="dateOfBirth">Ημ/νία Γέννησης</form:label></td>
				<td><fmt:formatDate value="${user.dateOfBirth}" pattern="dd-MM-yyyy" /></td>
				<td><form:input class="text-input" type="date" path="dateOfBirth" /></td>
			</tr>
			<tr>
				<td><form:label path="identityCardNO">Αριθμός Ταυτότητας</form:label></td>
				<td><c:out value="${user.identityCardNO}" /></td>
				<td><form:input class="text-input" path="identityCardNO" /></td>
			</tr>
			<tr>
				<td><form:label path="email">email</form:label></td>
				<td><c:out value="${user.email}" /></td>
				<td><form:input class="text-input" path="email" /></td>
			</tr>
			<tr>
				<td><form:label path="phone">Τηλέφωνο</form:label></td>
				<td><c:out value="${user.phone}" /></td>
				<td><form:input class="text-input" path="phone" /></td>
			</tr>
			<c:if test="${role == 'Clerk'}">
				<tr>
					<td><form:label path="supervising_dept">Τμήμα Επίβλεψης</form:label></td>
					<td><c:out value="${user.supervising_dept}" /></td>
					<td><form:input class="text-input" path="supervising_dept" /></td>
				</tr>
			</c:if>
			<c:if test="${role == 'Student'}">
				<tr>
					<td><form:label path="academicID">Αριθμός Ακαδημαϊκής Ταυτότητας</form:label></td>
					<td><c:out value="${user.academicID}" /></td>
					<td><form:input class="text-input" path="academicID" /></td>
				</tr>
				<tr>
					<td><form:label path="dept">Τμήμα</form:label></td>
					<td>
						<c:choose>
						  	<c:when test="${user.dept == 'DIT'}">
						  		Πληροφορικής και Τηλεματικής
						  	</c:when>
						  	<c:when test="${user.dept == 'GEO'}">
						  		Γεωγραφίας
						  	</c:when>
						  	<c:when test="${user.dept == 'DHEE'}">
						  		Οικιακής Οικονομίας
						  	</c:when>
						  	<c:when test="${user.dept == 'DDNS'}">
						  		Επιστήμης Διαιτολογίας – Διατροφής
						  	</c:when>
						  	<c:when test="${user.dept == 'AIRENG'}">
						  		Μηχανικών Αεροσκαφών
						  	</c:when>
						  	<c:otherwise>
						  		<c:out value="${user.dept}" /><br/>
						  		Yeah you should have removed me from the DB...						  		
						  	</c:otherwise>
					  </c:choose>
					</td>
					<td><form:select class="text-input" path="dept">
							<form:option value="DIT">Πληροφορικής και Τηλεματικής</form:option>
							<form:option value="GEO">Γεωγραφίας</form:option>
							<form:option value="DHEE">Οικιακής Οικονομίας</form:option>
							<form:option value="DDNS">Επιστήμης Διαιτολογίας – Διατροφής</form:option>
							<form:option value="AIRENG">Μηχανικών Αεροσκαφών</form:option>
						</form:select>
					</td>
					<!-- <td><form:input path="dept" /></td>-->
				</tr>
			</c:if>

		</table>
		<br />
		<input type="submit" class= "submit-button" value="ΑΠΟΘΗΚΕΥΣΗ"/>
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
	</div>
	</div>
