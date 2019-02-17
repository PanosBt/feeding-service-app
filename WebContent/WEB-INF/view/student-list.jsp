	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link href="<c:url value="/resources/css/striped-table.css"/>" rel="stylesheet" type="text/css">
	
	<div class = "container">
		<div class = "rounded-box">
			<div class = "center">
			
				<form method="POST"
					action="${pageContext.request.contextPath}/clerk/modify_student_search">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class=center>
					<table class="striped-table center-stuff" class="center">
						<tr>
							<th>Username</th>
							<th></th>
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
					</div>
				</form>
				<form action="${pageContext.request.contextPath}/clerk"
					method="get">
					<br />
					<button type="submit" class="submit-button">Πίσω</button>
					<br>
				</form>
			</div>
		</div>
	</div>	