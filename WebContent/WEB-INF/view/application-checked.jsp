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
<h4>Η αίτηση διεκπερεώθηκε με επιτυχία στο σύστημα!</h4>
<br>
<p>Πώς θα θέλατε να συνεχίσετε;</p>
<a href="${pageContext.request.contextPath}/clerk">Επιστροφή στην αρχική σελίδα</a>
<br>
<a href="${pageContext.request.contextPath}/clerk/applicationlist">Διεκπερέωση Αιτήσεων</a>
</body>
</html>