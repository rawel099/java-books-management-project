<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍　登録失敗画面</title>
</head>
<body>
	<c:if test="${message != null}">
    <p style="color:red;">${message}</p>
    <p>お手数ですが、再度内容を確認して登録お願いいたします。</p>
</c:if>
<p style="text-align: center"><a href="/mini2App/jsp/B_register.jsp">登録画面へ戻る</a></p>
</body>
</html>