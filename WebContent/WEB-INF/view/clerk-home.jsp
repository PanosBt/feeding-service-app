	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link href="<c:url value="/resources/css/text-input.css"/>" rel="stylesheet" type="text/css">
	<div class = "container">
		<div class = "rounded-box">
			<div class = "center">
				<h1><b>Καλώς ήρθατε</b> <b> ${userFirstName}</b> <b> ${userLastName}!</b></h1>
				<br />
				<br />
				<a href="${pageContext.request.contextPath}/clerk/studentlist" class="button1">Νέα Καρτέλα Φοιτητή</a>
				<br />
				<br />
				<a href="${pageContext.request.contextPath}/clerk/applicationlist" class="button1">Έλεγχος Αιτήσεων</a>
				<br />
				<br />
				<sec:authorize access="hasRole('ROLE_SUPERVISOR')">
					<h4>Τροποποίηση αριθμού επιλέξιμων φοιτητών ανα τμήμα</h4>
					<form:form method="POST"
						action="${pageContext.request.contextPath}/clerk/update_student_limit"
						modelAttribute="updatedLimit">
						<table class="striped-table center-stuff">
							<tr>
								<th>Όρια τμημάτων</th>
							</tr>
							<tr>
								<td>Τμήμα</td>
								<td>Τρέχων Όριο</td>
								<td>Νέο Όριο</td>
							</tr>
							<c:forEach var="limit" items="${limits}">
								<tr>
									<td>${limit.dept}</td>
									<td>${limit.student_limit}</td>
									<td><input type="text", path="student_limit", class="text-input"/></td>
									<td><button type="submit" name="dept"
											value=${limit.dept} path="dept" class="submit-button">Αποθήκευση</button></td>
								</tr>
							</c:forEach>							
	<!-- 						<tr>
								<td>Πληροφορικής και Τηλεματικής</td>
								<td>20</td>
								<td><input type="text" required name="limit" class="text-input" ></td>
								<td><button type="submit" class="submit-button">Εισαγωγή</button></td>
							</tr>
							<tr>
								<td>Οικιακής Οικονομίας</td>
								<td>20</td>
								<td><input type="text" required name="limit" class="text-input" ></td>
								<td><button type="submit" class="submit-button">Εισαγωγή</button></td>
							</tr>
							<tr>
								<td>Γεωγραφίας</td>
								<td>20</td>
								<td><input type="text" required name="limit" class="text-input" ></td>
								<td><button type="submit" class="submit-button">Εισαγωγή</button></td>
							</tr>
							<tr>
								<td>Επιστήμης Διαιτολογίας – Διατροφής</td>
								<td>25</td>
								<td><input type="text" required name="limit" class="text-input" ></td>
								<td><button type="submit" class="submit-button">Εισαγωγή</button></td>
							</tr>
							<tr>
								<td>Μηχανικών Αεροσκαφών</td>
								<td>20</td>
								<td><input type="text" required name="limit" class="text-input" ></td>
								<td><button type="submit" class="submit-button">Εισαγωγή</button></td>
							</tr>   -->
						</table>
					</form:form>
				</sec:authorize>
			</div>
		</div>
	</div>

