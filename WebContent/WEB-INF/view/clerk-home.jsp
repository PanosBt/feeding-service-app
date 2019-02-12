	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link href="<c:url value="/resources/css/text-input.css"/>" rel="stylesheet" type="text/css">

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
				<h4>Τροποποίηση αριθμού επιλέξιμων φοιτητών</h4>
				<br /> Τρέχον όριο φοιτητών: ${limit}
				<br />
				<br />
				<form method="POST"
					action="${pageContext.request.contextPath}/clerk/update_student_limit">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
						<div class='center'>
							<input type="text" required name="limit" class="text-input" > 
							<br />
							<br />
							<button type="submit" class="submit-button">Εισαγωγή</button>
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
			</sec:authorize>
		</div>
	</div>
