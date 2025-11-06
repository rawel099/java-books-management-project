<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
	

		<meta charset="UTF-8">
		<title>ログイン画面</title>
		<link rel="stylesheet" type="text/css" href="/mini2App/css/stylesheet.css"/>
<%--		<link rel="stylesheet" type="text/css" href="css/stylesheet.css">  --%>
	</head>
<body>
	<h1>ログイン画面</h1>
	
		<%@ page import="java.util.Calendar, java.text.SimpleDateFormat" %>
<%
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 (E)", java.util.Locale.JAPANESE);
    String today = sdf.format(cal.getTime());

    int month = cal.get(Calendar.MONTH) + 1;
    String monthImg = "/resources/images/month/" + String.format("%02d", month) + ".png";
%>

<div class="date">** 本日は<%= today %>です。**</div>
<br>
<div class="month-image">
    <img src="<%= request.getContextPath() + monthImg %>" alt="月別イメージ" width="180">
</div>
<br>
<br>
	<!-- ログイン失敗時のエラーメッセージ表示 -->
	<c:if test="${loginError != null}">
		<div style="color:red;">
			<p>${loginError}</p>
			</div>
	</c:if>
		<form action="/mini2App/login" method="post">
			<table>
				<tr>
					<th>ユーザーID</th>
					<td><input type="text" name="loginId"></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
			<input type="submit" value="ログイン">
		</form>
		<p><a href="/mini2App/register">新規会員登録はこちら</a></p>

</body>
</html>