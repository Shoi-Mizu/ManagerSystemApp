<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.UserBean" %>
<%@ page import="model.TimeBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% UserBean user = (UserBean)session.getAttribute("uBean"); %>
	<% String message = (String)request.getAttribute("message"); %>
	<% String logind = (String)request.getAttribute("logind"); %>
	
	<h1>ユーザー設定ページ</h1>
	<p></p>
	
	<% if(!(message == null)){ %>
		<% if(!message.isEmpty()){ %>
			<%= message %>
		<% } %>
	<% } %>
	<p></p>
	
	<p></p>
	<p>現在の名前は[ <%= user.getName() %> ]です</p>
	<form action="userSetting" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">	
		<input type="hidden" name="change" value="name">
		新しい名前<input type="text" name="name">	
		<button type="submit">変更する</button>
	</form>
	<br>
	
	<p></p>
	<p>現在のIDは[ <%= user.getUserId() %> ]です</p>
	<form action="userSetting" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">	
		<input type="hidden" name="change" value="id">
		新しいID<input type="text" name="id">	
		<button type="submit">変更する</button>
	</form>
	<br>

	<p></p>
	<p>現在のパスワードは[ <%= user.getUserPass() %> ]です</p>
	<form action="userSetting" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">	
		<input type="hidden" name="change" value="userPass">
		新しいパスワード<input type="text" name="userPass">	
		<button type="submit">変更する</button>
	</form>
	<br>
	
	<p></p>
	<form action="userDelete" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">
		<button type="submit">ユーザーを削除する</button>
	</form>
	
	<p></p>
	<form action="home" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">
		<button type="submit">戻る</button>
	</form>
</body>
</html>