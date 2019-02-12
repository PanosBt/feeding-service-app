<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div class = "rounded-box">
	<div class = "center">
	
	<h2>Σύνδεση</h2>
	

	<form:form action="${pageContext.request.contextPath}/authUser"
		method="POST">
		<table>
			<c:if test="${param.error != null}">
				<tr> 
					<td><div class="error">Τα στοιχεία σύνδεσης είναι λάθος</div></td>
				</tr>
			</c:if>
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
	</div>
	</div>