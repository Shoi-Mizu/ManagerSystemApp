<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<% UserBean user = (UserBean)request.getAttribute("user"); %>
	<h1><%= user.getName() %>さんのマイページ</h1>
	<p></p>
	<a href="/">勤務表示</a>
	<p></p>
	<a href="/">アカウントを削除する</a>
</body>
</html>