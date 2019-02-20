<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="<c:url value="/resources/css/striped-table.css"/>" rel="stylesheet" type="text/css">
<div class="container">	
	<div class="rounded-box">
		<div class="center">	
			<h4>Η αίτηση διεκπερεώθηκε με επιτυχία στο σύστημα!</h4>
			<br>
			<p>Πώς θα θέλατε να συνεχίσετε;</p>
			<a class= "asubmit-button" href="${pageContext.request.contextPath}/clerk">Επιστροφή στην αρχική σελίδα</a>
			<br>
			<br>
			<a class= "asubmit-button" href="${pageContext.request.contextPath}/clerk/applicationlist">Διεκπερέωση Αιτήσεων</a>
		</div>
	</div>
</div>	