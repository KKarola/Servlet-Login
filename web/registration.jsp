<%--
  Created by IntelliJ IDEA.
  User: Karolina
  Date: 2018-03-10
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>/

<h1>Welcome, please login</h1>

<form action="/registration" method="post">
    First-name: <input type="text" name="firstname" width="30"/>
    Last-name: <input type="text" name="lastname" width="30"/>
    E-mail: <input type="text" name="mail" width="30"/>
    Login-name: <input type="text" name="loginname" width="30"/>
    Password: <input type="password" name="password" width="10"/>
    <input type="submit" value="registration"/>
</form>

<p style="color:red;">${errorMessage}</p>

</body>
</html>
