<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規アカウント作成</title>
</head>
<body>
	<h1>新規アカウントを作成します</h1>
	<p></p>
	<% String message = (String)request.getAttribute("message"); %>
	<%= message %>
	<p></p>
	<form action="create" method="post">
	<label for="userName">アカウント名</label>
	<input type="text" name="userName">
	<p></p>
	<label for="password">パスワード</label>
	<input type="password" name="userPass">
    <p></p>
    <button type="submit">登録する</button>
	</form>

	<p></p>
	<a href="/ManagerSystem">トップページに戻る</a>
</body>
</html>