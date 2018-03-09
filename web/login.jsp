<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Karolina
  Date: 2018-03-07
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>

<body>
<h1>Welcome, please login</h1>

<form action="/login" method="post">
    Login-name: <input type="text" name="loginname" width="30"/>
    Password: <input type="password" name="password" width="10"/>
    <input type="submit" value="login"/>
</form>

<p style="color:red;">${errorMessage}</p>

</body>
</html>