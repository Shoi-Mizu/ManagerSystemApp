<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠状況</title>
</head>
<body>
	<% String logind = (String)request.getAttribute("logind");
	   int year = (int)request.getAttribute("year");
	   int month = (int)request.getAttribute("month");
	   @SuppressWarnings("unchecked")
	   ArrayList<String> week = (ArrayList<String>)request.getAttribute("week");
	   @SuppressWarnings("unchecked")
	   ArrayList<String> admission = (ArrayList<String>)request.getAttribute("admission");
	   @SuppressWarnings("unchecked")
	   ArrayList<String> leaving = (ArrayList<String>)request.getAttribute("leaving");
	   String changeMonth = ""; 															%>

	<form action="home" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">
		<button type="submit">戻る</button>
	</form>
	<p></p>
	   
	<form action="attendance" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">
		<% changeMonth = "before"; %>
		<input type="hidden" name="changeMonth" value="<%= changeMonth %>">
		<input type="hidden" name="year" value="<%= String.valueOf(year) %>">
		<input type="hidden" name="month" value="<%= String.valueOf(month) %>">
		<button type="submit" name="before">前月</button>
	</form>	
		
	<form action="attendance" method="post">
		<input type="hidden" name="logind" value="<%= logind %>">
		<% changeMonth = "after"; %>
		<input type="hidden" name="changeMonth" value="<%= changeMonth %>">
		<input type="hidden" name="year" value="<%= String.valueOf(year) %>">
		<input type="hidden" name="month" value="<%= String.valueOf(month) %>">
		<button type="submit" name="after">次月</button>
	</form>
	
	<p><%= year %>年<%= month + 1 %>月</p>
	   
	<!-- カレンダー表示部 -->
	<table>
		<thead>
			<tr>
				<th>曜日</th>
				<th>日付</th>
				<th>出勤時間</th>
				<th>退勤時間</th>
			</tr>
		</thead>
		<tbody>
			<% for(int i = 0; i < week.size(); i++){ %>
			<tr>
				<th><%= week.get(i) %></th>
				<th><%= i + 1 %></th>
				<th><%= admission.get(i) %></th>
				<th><%= leaving.get(i) %></th>
			</tr>
			<% } %>   
		</tbody>
	</table>   
</body>
</html>