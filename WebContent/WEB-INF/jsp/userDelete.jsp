<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.UserBean" %>
<%@ page import="model.TimeBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー削除</title>
</head>
<body>
	<% UserBean user = (UserBean)session.getAttribute("uBean"); %>
	<% String logind = (String)request.getAttribute("logind"); %>
	
	<h1>本当に削除してもよろしいですか？</h1>
	
	<p>削除するユーザー[ <%= user.getName() %> ]</p>
	<form action="userDelete" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">	
		<input type="hidden" name="delete" value="delete">
		<button type="submit">削除する</button>
	</form>
	
	
	<p></p>
	<form action="userSetting" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">
		<input type="submit" value="戻る">
	</form>	
	
	<p></p>
	<form action="home" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">
		<input type="submit" value="ホームに戻る">
	</form>	

</body>
</html>