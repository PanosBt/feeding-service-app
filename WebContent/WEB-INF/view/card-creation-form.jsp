<%--
  Created by IntelliJ IDEA.
  User: Serelia
  Date: 12/17/2018
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Δημιουργία Καρτέλας Φοιτητή</title>
</head>
<body>
<form action="card-created" method="POST"><br>
    Όνομα: <input type="text" name="firstName"><br>
    Επώνυμο: <input type="text" name="lastName"><br>
    Αριθμός Μητρώου: <input type="text" name="academicID"><br>
    Ταυτότητα: <input type="text" name="identityCardNO"><br>
    Τηλέφωνο: <input type="text" name="phone"><br>
    Email: <input type="text" name="email"><br>
	<select name="dept">
		<option value="empty">Παρακαλώ επιλέξτε τμήμα...</option>
		<option value="geo">Τμήμα Γεωγραφίας</option>
		<option value="ddns"> Επιστήμης Διαιτολογίας-Διατροφής</option>
		<option value="dee">Τμήμα Οικιακής Οικονομίας και Οικολογίας</option>
		<option value="dit">Τμήμα Πληροφορικής και Τηλεματικής</option>
		<option value="tour">International Master of Sustainable Tourism Development</option>
   </select>
   <input type="submit" value="submit">
</form>

</body>
</html>
