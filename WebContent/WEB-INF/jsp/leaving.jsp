<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.TimeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% String logind = (String)request.getAttribute("logind"); %>
	<% TimeBean time = (TimeBean)session.getAttribute("tBean"); %>
	<h1>退勤完了しました！</h1>
	<p></p>
	<p><%= time.getAdmission() %> に退勤しました！</p>
	<p>今日も1日お疲れ様でした！！</p>
	<p></p>
	<form action="home" method="post">
	<input type="hidden" name="logind" value="<%= logind %>">
	<button type="submit">戻る</button>
	</form>
</body>
</html>