	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="<c:url value="/resources/css/striped-table.css"/>" rel="stylesheet" type="text/css">

<div class="container">
	<div class="rounded-box">
		<div class="center">	
			
			<table class="striped-table center-stuff">
				<tr>
				<th><h4>Στοιχεία Φοιτητή</h4></th>
				<th></th>
				</tr>
				<tr>
					<td>Όνομα</td>
					<td>${student.firstName}</td>
				</tr>
				<tr>
					<td>Επίθετο</td>
					<td>${student.lastName}</td>
				</tr>
				<tr>
					<td>Ημ/νία Γέννησης</td>
					<td><fmt:formatDate pattern="dd-MM-yyyy"
							value="${student.dateOfBirth}" /></td>
				</tr>
				<tr>
					<td>Αριθμός Ταυτότητας</td>
					<td>${student.identityCardNO}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${student.email}</td>
				</tr>
				<tr>
					<td>Τηλέφωνο</td>
					<td>${student.phone}</td>
				</tr>
				<tr>
					<td>Αριθμός Ακαδημαϊκής Ταυτότητας</td>
					<td>${student.academicID}</td>
				</tr>
				<tr>
					<td>Τμήμα</td>
					<td>${student.dept}</td>
				</tr>
			</table>
			<br />
			<br/>
			<table class="striped-table center-stuff">
				<tr>
				<th><h4>Στοιχεία Αίτησης</h4></th>
				<th></th>
				</tr>
				<tr>
					<td>Οικογενιακό Εισόδημα</td>
					<td>${application.familyIncome}</td>
				<tr>
					<td>Αριθμός Αδερφών</td>
					<td>${application.num_siblings}</td>
				</tr>
				<tr>
					<td>Πόλη Καταγωγής</td>
					<td>${application.origin_city }</td>
				</tr>
				<tr>
					<td>Μητέρα</td>
					<td><c:choose>
							<c:when test="${application.mother_employeed}">
							Εργαζόμενη
						</c:when>
							<c:otherwise>
							Άνεργη
						</c:otherwise>
						</c:choose></td>
				<tr>
					<td>Πατέρας</td>
					<td><c:choose>
							<c:when test="${application.father_employeed}">
							Εργαζόμενος
						</c:when>
							<c:otherwise>
							Άνεργος
						</c:otherwise>
						</c:choose></td>
			</table>
			<br/>
			<br/>
			<form method="POST"
				action="${pageContext.request.contextPath}/clerk/document"
				target="_blank">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<table class="striped-table center-stuff">
					<tr>
						<th>Τύπος εγγράφου</th>
						<th></th>
					</tr>
					<c:forEach var="accompanyingDocuments" items="${accompanyingDocuments}">
						<tr>
							<td>
							  <c:choose>
							  	<c:when test="${accompanyingDocuments.doc_type == 'EKK'}">
							  		Εκκαθαριστικό Εφορίας
							  	</c:when>
							  	<c:when test="${accompanyingDocuments.doc_type == 'POK'}">
							  		Πιστοποιητικό Οικογενιακής Κατάστασης
							  	</c:when>
							  	<c:when test="${accompanyingDocuments.doc_type == 'PK'}">
							  		Πιστοποιητικό Κατοικιας
							  	</c:when>
							  	<c:when test="${accompanyingDocuments.doc_type == 'BAM'}">
							  		Βεβαιωση Ανεργιας - Μητέρα
							  	</c:when>
							  	<c:when test="${accompanyingDocuments.doc_type == 'BAP'}">
							  		Βεβαιωση Ανεργιας - Πατέρας
							  	</c:when>
							  </c:choose>
							</td>
							<td><button type="submit" class="submit-button" name="file_path"
									value=${accompanyingDocuments.file_path } >Έλεγχος</button></td>
						</tr>
					</c:forEach>
				</table>
			</form>
			<br>
			<br>
			<form method="POST"
			action="${pageContext.request.contextPath}/clerk/applcationchecked/${application.appl_id}">
			<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
			<button type="submit" name="approve"
				value=true class="submit-button">Αποδοχή</button>
			<button type="submit" name="approve"
				value=true class="submit-button">Απόρριψη</button>
			</form>
		</div>
	</div>
</div>


























