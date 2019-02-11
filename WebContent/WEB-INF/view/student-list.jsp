	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<div class = "rounded-box">
		<div class = "center">
			<form method="POST"
				action="${pageContext.request.contextPath}/clerk/modify_student_search">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<table border=1>
					<tr>
						<th>Username</th>
					</tr>
					<!-- loop over and print our students -->
					<c:forEach var="tempStudent" items="${students}">
						<tr>
							<td>${tempStudent.username}</td>
							<td><button type="submit" name="username"
									value=${tempStudent.username }  class="submit-button">Επεξεργασία</button></td>
						</tr>
					</c:forEach>
				</table>
			</form>
			<form action="${pageContext.request.contextPath}/clerk"
				method="get">
				<br />
				<button type="submit" class="submit-button">Πίσω</button>
				<br>
			</form>
		</div>
	</div>