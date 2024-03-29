	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link href="<c:url value="/resources/css/simple-table.css"/>" rel="stylesheet" type="text/css">
	<div class = "container">
		<div class = "rounded-box">
			<div class = "center">
			
				<div class="welcome-admin-text">
					<h2>Καλώς ήρθατε ${userFirstName} ${userLastName}!</h2>
				</div>
			
				<div class="admin-functions">
					<h4>Επιλογές Διαχειριστή</h4>
			
					<h5>Δημιουργία νέου χρήστη</h5>
					<div class="admin-create-form">
						<form:form method="POST"
							action="${pageContext.request.contextPath}/admin/create_user"
							modelAttribute="user">
							<table>
								<tr>
									<td><form:label path="username">Όνομα χρήστη</form:label></td>
									<td><form:input class = "text-input" path="username" /></td>
									<c:if test="${not empty userExists && userExists}">
										<td><div class="error error-color">Αυτό το username
												χρησιμοποιείται ήδη.</div></td>
									</c:if>
									<td><form:errors path="username" /></td>
								</tr>
								<tr>
									<td><form:label path="password">Κωδικός</form:label></td>
									<td><form:input class="text-input" path="password" /></td>
									<td><form:errors path="password" /></td>
								</tr>
								<tr>
									<td><form:label path="userType">Τύπος χρήστη</form:label></td>
									<td><form:select class="text-input" path="userType">
											<form:option value="clerk">Clerk</form:option>
											<form:option id="student_option" value="student">Student</form:option>
											<form:option value="supervisor">Supervisor</form:option>
											<form:option value="admin">Administrator</form:option>
										</form:select></td>
								<tr id= "student_dept" hidden> 
									<td><form:label path="dept">Τμήμα</form:label></td>
									<td>
										<form:select class="text-input" path="dept">
											<form:option value="DIT">Πληροφορικής και Τηλεματικής</form:option>
											<form:option value="GEO">Γεωγραφίας</form:option>
											<form:option value="DHEE">Οικιακής Οικονομίας</form:option>
											<form:option value="DDNS">Επιστήμης Διαιτολογίας – Διατροφής</form:option>
											<form:option value="AIRENG">Μηχανικών Αεροσκαφών</form:option>
										</form:select>
									</td>
								</tr>
								<tr>
									<td><input type="submit" class="submit-button" value="ΔΗΜΙΟΥΡΓΙΑ" /></td>
								</tr>
							</table>
						</form:form>
					</div>
					<c:if test="${userCreated}">
						<tr>
							<td>Ο χρήστης δημιουργήθηκε!</td>
						</tr>
					</c:if>
					<c:if test="${not empty userCreated && not userCreated}">
						<tr>
							<td><div class="error error-color">Η δημιουργία του χρήστη απέτυχε.</div></td>
						</tr>
					</c:if>
					<br />
					<hr>
					<h5>Τροποποίηση Στοιχείων Χρήστη</h5>
			
					<div class="admin-search-user-for-mod">
						<form method="POST"
							action="${pageContext.request.contextPath}/admin/modify_user_search">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<table>
								<tr>
									<td>Όνομα χρήστη</td>
									<td><input type="text" class="text-input" required name="username"></td>
									<td><c:if
											test="${not empty modUserFound && not modUserFound}">
											<div class="error error-color">Ο χρήστης δεν βρέθηκε</div>
										</c:if></td>
								</tr>
								<tr>
									<td><input type="submit" class="submit-button" value="ΑΝΑΖΗΤΗΣΗ"></td>
								</tr>
							</table>
						</form>
					</div>
					<br />
					<hr>
					<h5>Διαγραφή Χρήση</h5>
					<div class="admin-delete-user">
						<form method="POST"
							action="${pageContext.request.contextPath}/admin/delete_user">
							<input type="hidden" class="text-input" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<table>
								<tr>
									<td>Όνομα χρήστη</td>
									<td><input type="text" class="text-input" class="text-input" required name="username"></td>
								</tr>
								<tr>
									<td><input type="submit" class="submit-button"  value="ΔΙΑΓΡΑΦΗ"></td>
									<c:if test="${not empty delUserFound}">
										<td><c:choose>
												<c:when test="${not delUserFound}">
													<div class="error error-color">Ο χρήστης δεν βρέθηκε</div>
												</c:when>
												<c:otherwise>
													<div class="success">Ο χρήστης διαγράφηκε!</div>
												</c:otherwise>
											</c:choose></td>
			
									</c:if>
								</tr>
							</table>
						</form>
					</div>
			
				</div>
			</div>
		</div>
	</div>	
