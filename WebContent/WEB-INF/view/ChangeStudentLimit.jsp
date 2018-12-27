<%--
  Created by IntelliJ IDEA.
  User: Serelia
  Date: 12/19/2018
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ChangeStudentLimit</title>
</head>
<body>
<form action="ChangeStudentLimitController" method="POST">
<p style="font-size:30px;">Το όριο των φοιτητών αυτη τη στιγμή είναι: <%--${limit}--%></p>
<%--Need To be checked server side i believe, best solution i came up with w/o js --%>
	<input type="number" name = "newLimit">
<input type="submit" value="submit">

</form>
</body>
</html>
