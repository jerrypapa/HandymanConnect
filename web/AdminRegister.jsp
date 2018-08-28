<%--
  Created by IntelliJ IDEA.
  User: papa
  Date: 7/12/18
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
              pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><centre>Admin Registration Form<centre/></title>
</head>
<body>
<h1>Admin Register Form</h1>
<form action="MServlet" method="post">
    <table style="with: 50%">
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstname" /></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastname" /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><input type="text" name="address" /></td>
        </tr>
        <tr>
            <td>Contact No</td>
            <td><input type="text" name="phoneno" /></td>
        </tr></table>
    <input type="submit" value="Submit" /></form>
</body>
</html>
