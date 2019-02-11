	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link href="<c:url value="/resources/css/text-input.css"/>" rel="stylesheet" type="text/css">

	<div class = "rounded-box">
	<div class = "center">
	<h4>Καλώς ήρθατε!</h4>
	<h4>Επιλογές χρήστη:</h4>
	<br />
	<br />
	<a href="${pageContext.request.contextPath}/clerk/studentlist" class="button1">Νέα Καρτέλα Φοιτητή</a>
	<br />
	<br />
	<a href="${pageContext.request.contextPath}/clerk/applicationlist" class="button1">Έλεγχος Αιτήσεων</a>
	<br />
	<br />
	<h4>Τροποποίηση αριθμού επιλέξιμων φοιτητών</h4>
	<br /> Τρέχον όριο φοιτητών: ${limit}
	<br />
	<br />
	<form method="POST"
		action="${pageContext.request.contextPath}/clerk/update_student_limit">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
			<div class='center'>
		<table>
			<tr>
				<td><input type="text" required name="limit" class="text-input" ></td> 
				
			</tr>
			<tr>
				<td>
					<button type="submit">Εισαγωγή</button>
				</td>
			</tr>
		</table>
		</div>
	</form>
	<c:if test="${not empty studentLimitUpdated}">
		<c:choose>
			<c:when test="${studentLimitUpdated}">
				<div>Το όριο ενημερώθηκε επιτυχώς!</div>
			</c:when>
			<c:otherwise>
				<div class="error">Η ενημέρωση απέτυχε</div>
			</c:otherwise>
		</c:choose>
	</c:if>
	</div>
	</div>
