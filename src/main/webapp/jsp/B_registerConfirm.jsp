<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書登録確認</title>
</head>
<body>
<h3>以下の内容で登録しますか？</h3>
<table border="1">
    <tr><th>タイトル</th><td>${title}</td></tr>
    <tr><th>著者</th><td>${author}</td></tr>
    <tr><th>出版社</th><td>${publisher}</td></tr>
    <tr><th>ジャンル</th><td>${genre}</td></tr>
    <tr><th>収納棚</th><td>${book_area}</td></tr>
</table>

<form action="/mini2App/registerBook" method="post">
    <input type="hidden" name="title" value="${title}">
    <input type="hidden" name="author" value="${author}">
    <input type="hidden" name="publisher" value="${publisher}">
    <input type="hidden" name="genre" value="${genre}">
    <input type="hidden" name="book_area" value="${book_area}">
    <input type="submit" value="登録する">
</form>
</body>
</html>