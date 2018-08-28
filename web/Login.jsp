<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login</h1>

<form action="LoginServlet" id="form1" method="get" autocomplete="off">
        <label>Email:</label><br/>
        <input name="username" value=""/>
        <label>Password:</label><br/>
        <input type="password" value="" name="password"/>
    <br/><br/>
        <input type="submit" value="Submit"/>
</form>

</body>
</html>