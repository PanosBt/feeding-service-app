<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<h3>Σύνδεση</h3>
	
	
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
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td><label>Κωδικός</label></td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="ΣΥΝΔΕΣΗ" /></td>
			</tr>
		</table>
	</form:form>