<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍　登録完了画面</title>
</head>
<body>
	<h2 style="color:red;">登録が完了致しました。</h2>
    <c:if test="${message != null}">
        <p style="color:red;">${message}</p>
    </c:if>

    <p style="text-align: center">
        <a href="${pageContext.request.contextPath}/jsp/B_register.jsp">登録画面へ戻る</a>
    </p>
<p style="text-align: center"><a href="/mini2App/jsp/book_edit.jsp">メンテナンス画面へ戻る</a></p>
</body>
</html>

