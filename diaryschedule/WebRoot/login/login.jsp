<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆</title>
</head>
<body>
	<form action = "../login" method = "post">
		<span>登陆ID： </span><input type = "text" name = "id" /><br/>
		<span>密码： </span><input type = "password" name = "password" /><br>
		<input type = "submit" value = "登陆" />
	</form>
</body>
</html>