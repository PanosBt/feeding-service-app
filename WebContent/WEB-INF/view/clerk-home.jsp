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
					<form method="POST"	action="${pageContext.request.contextPath}/clerk/update_student_limit">
						<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
						<table class="striped-table center-stuff">
						<!-- 
							<tr>
								<th>Όρια τμημάτων</th>
							</tr>
						 -->
							<tr>
								<td>Τμήμα</td>
								<td>Τρέχων Όριο</td>
								<td>Νέο Όριο</td>
								<td>Αποθήκευση</td>
							</tr>
							<c:forEach var="limit" items="${limits}">
								<tr>
								<!-- 
									<td>${limit.dept}</td>
								 -->
									<td>
									<c:choose>
										<c:when test="${limit.dept == 'DIT'}">
											Πληροφορικής και Τηλεματικής
										</c:when>
										<c:when test="${limit.dept == 'GEO'}">
											Γεωγραφίας
										</c:when>
										<c:when test="${limit.dept == 'DHEE'}">
											Οικιακής Οικονομίας
										</c:when>
										<c:when test="${limit.dept == 'DDNS'}">
											Επιστήμης Διαιτολογίας – Διατροφής
										</c:when>
										<c:when test="${limit.dept == 'AIRENG'}">
											Μηχανικών Αεροσκαφών
										</c:when>
									</c:choose>
									</td>
									<td>${limit.student_limit}</td>
									<td><input type="number" class="text-input" name="newLimit"/></td>
									<td>
										<button type="submit" name="dept" value="${limit.dept}" class="submit-button">
											Αποθήκευση
										</button>
									</td>
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
					</form>
					<c:if test="${not empty limitUpd}">
						<c:choose>
							<c:when test="${not limitUpd}">
								<div class="error error-color">Η ενημέρωση του ορίου που επιλέξατε απέτυχε</div>
							</c:when>
							<c:otherwise>
								<div class="success">Η ενημέρωση του ορίου που επιλέξατε ολοκληρώθηκε!</div>
							</c:otherwise>
						</c:choose>

					</c:if>
				</sec:authorize>
			</div>
		</div>
	</div>

