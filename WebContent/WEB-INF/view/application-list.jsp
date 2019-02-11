	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link href="<c:url value="/resources/css/striped-table.css"/>" rel="stylesheet" type="text/css">
	<div class="rounded-box">
	<div class="center">
	<form method="POST"
		action="${pageContext.request.contextPath}/clerk/applicationinfo">
		<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
		<table class="striped-table">
			<tr>
				<th>ID Αίτησης</th>
				<th></th>
			</tr>
			<c:forEach var="application" items="${applications}">
				<tr>
					<td>${application.appl_id}</td>
					<td><button type="submit" name="appl_id"
							value=${application.appl_id }  class="submit-button">Επεξεργασία</button></td>
				</tr>
			</c:forEach>
		</table>
		
	</form>
	</div>
	</div>