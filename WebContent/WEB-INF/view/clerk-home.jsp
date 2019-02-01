<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Clerk Home Page</h4>
	<br />
	<br />
	<a href="${pageContext.request.contextPath}/clerk/studentlist">Νέα Καρτέλα Φοιτητή</a>
	<br />
	<br />
	<hr style="border: 1px solid black;" />
	<h4>Τροποποίηση αριθμού επιλέξιμων φοιτητών</h4>
	<br /> Τρέχον όριο φοιτητών: ${limit}
	<br />
	<br />
	<form method="POST"
		action="${pageContext.request.contextPath}/clerk/update_student_limit">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<table>
			<tr>
				<td><input type="number" required name="limit"></td>
			</tr>
			<tr>
				<td>
					<button type="submit">Εισαγωγή</button>
				</td>
			</tr>
		</table>
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
</body>
</html>