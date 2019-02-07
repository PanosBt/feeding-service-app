<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Στοιχεία Φοιτητή</h4>
	<table border="1">
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
	<h4>Στοιχεία Αίτησης</h4>
	<table border="1">
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
	<br>
	<form method="POST"
		action="${pageContext.request.contextPath}/clerk/document"
		target="_blank">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<table border=1>
			<tr>
				<th>Τύπος εγγράφου</th>
			</tr>
			<c:forEach var="accompanyingDocuments" items="${accompanyingDocuments}">
				<tr>
					<td>${accompanyingDocuments.doc_type}</td>
					<td><button type="submit" name="file_path"
							value=${accompanyingDocuments.file_path } class="ui button">Έλεγχος</button></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>





























