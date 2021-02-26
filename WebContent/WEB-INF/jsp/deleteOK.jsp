<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー削除</title>
</head>
<body>
	<% String message = (String)request.getAttribute("message"); %>
	<h1><%= message %></h1>

	<p></p>
	<a href="/ManagerSystem">トップページに戻る</a>
</body>
</html>