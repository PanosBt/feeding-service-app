<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div class = "rounded-box">
	<div class = "center">
	
	<h2>Σύνδεση</h2>
	

	<form:form action="${pageContext.request.contextPath}/authUser"
		method="POST">
		<table class="center-stuff">
			<tr>
				<td><label>Όνομα Χρήστη</label></td>
				<td><input type="text" class = "text-input" name="username" /></td>
			</tr>
			<tr>
				<td><label>Κωδικός</label></td>
				<td><input type="password" class = "text-input" name="password" /></td>
			</tr>
			
		</table>
		<input type="submit" class = "submit-button" value="ΣΥΝΔΕΣΗ" />
	</form:form>
	<br />
	<c:if test="${param.error != null}">
				<div class="error"><p class="error-color">Τα στοιχεία σύνδεσης είναι λάθος</p></div>
			</c:if>
	</div>
	</div>