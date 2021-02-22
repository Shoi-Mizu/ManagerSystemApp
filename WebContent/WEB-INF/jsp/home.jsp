<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.UserBean" %>
<%@ page import="model.TimeBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<% UserBean user = (UserBean)request.getAttribute("user"); %>
	<% TimeBean time = (TimeBean)request.getAttribute("time"); %>
	<% String errMessage = (String)request.getAttribute("errMessage"); %>
	
	<h1><%= user.getName() %>さんのマイページ</h1>
	<p></p>
	
	<% if(!(errMessage == null)){ %>
		<p><%= errMessage %></p>
	<% } %>
	<p></p>
	
	<form action="admission" method="post">
	<input type="hidden" name="userId" value="<%= user.getUserId() %>">
	<button type="submit" name=>出勤する</button>
	</form>
	<% if(time != null){ %>
		<% if(!time.getAdmission().equals("")){ %>
			<p>本日は[ <%= time.getAdmission() %> ]に出勤しています</p>
		<% } %>
	<% } %>
	<p></p>
	
	<form action="leave" method="post">
	<button type="submit">退勤する</button>
	</form>
	<p></p>
	
	<a href="/">勤怠表示</a>
	<p></p>
	<a href="/">アカウントを削除する</a>
	<p></p>
	<a href="/ManagerSystem">トップページに戻る</a>
</body>
</html>