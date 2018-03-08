<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Karolina
  Date: 2018-03-07
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>My Web App</title>
</head>
<body>
<h1>Hello!</h1>
<p>My first Web App</p>

<%
  Date date = new Date();
  out.print("<h5>" + date.toString() + "</h5>");
%>

</body>
</html>