<%--
  Created by IntelliJ IDEA.
  User: Serelia
  Date: 12/19/2018
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Παρακαλώ εισάγετε το ΑΜ του φοιτητή:</p><br>
    <form action="FindStudentServlet" method="post">
     <input type="text" name="StudentAM"><br>
     <input type="submit" value="submit">
     
    </form>

</body>
</html>
