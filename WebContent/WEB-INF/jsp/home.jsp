<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<% String name = (String)request.getAttribute("userName"); %>
	<h1><%= name %>さんのマイページ</h1>
	<p></p>
	<a href="/">勤務表示</a>
	<p></p>
	<a href="/">アカウントを削除する</a>
</body>
</html>