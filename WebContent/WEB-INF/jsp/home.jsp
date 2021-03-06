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
	<% UserBean user = (UserBean)session.getAttribute("uBean"); %>
	<% TimeBean time = (TimeBean)session.getAttribute("tBean"); %>
	<% String errMessage = (String)request.getAttribute("errMessage"); %>
	<% String logind = (String)request.getAttribute("logind"); %>
	
	<h1><%= user.getName() %>さんのマイページ</h1>
	<p></p>
	
	<% if(!(errMessage == null)){ %>
		<%= errMessage %>
	<% } %>
	<p></p>
	
	<form action="admission" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">	
		<button type="submit">出勤する</button>
	</form>
	<% if(time != null){ %>
		<% if(!(time.getAdmission() == null)){ %>
			<% if(!time.getAdmission().equals("")){ %>
				<p>本日は[ <%= time.getAdmission() %> ]出勤しています</p>
			<% } %>
		<% } %>
	<% } %>
	<p></p>
	
	<form action="leaving" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">	
		<button type="submit">退勤する</button>
	</form>
	<% if(time != null){ %>
		<% if(!(time.getLeaving() == null)){ %>
			<% if(!time.getLeaving().equals("")){ %>
				<p>本日は[ <%= time.getLeaving() %> ]に退勤しています</p>
			<% } %>
		<% } %>
	<% } %>
	<p></p>
	
	<form action="attendance" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">
		<input type="submit" name="display" value="勤怠表示">
	</form>
	<p></p>
	
	
	<form action="userSetting" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">
		<input type="submit" name="myUsesr" value="ユーザーの設定をする">
	</form>
	<p></p>
	
	<form action="logout" method="get">
		<input type="submit" name="toIndex" value="トップページに戻る">
	</form>
</body>
</html>