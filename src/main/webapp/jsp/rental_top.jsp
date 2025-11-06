<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>書籍貸出</title>
</head>
<body>
	<h2>*** 借りたいタイトルを選んでください。****</h2>
	<!-- <h3>※ ${user.name} 様は　あと${3 - user.rental_books} 冊まで借りられます。</h3> -->
 
 <!-- 機能実装前 
<form action="${pageContext.request.contextPath}/rental" method="get" style="margin-bottom:10px;">
    <input type="hidden" name="sort" value="title">
    <input type="submit" value="作品名順に並び替え">
</form>　　-->


  <p style="text-align: left">
      <img src="<%= request.getContextPath() %>/resources/book_hirameki_keihatsu_woman.png" 
     onerror="this.src='<%= request.getContextPath() %>/resources/images/book_hirameki_keihatsu_woman.png';"
     alt="ユーザー画像" width="100" height="100">
<form action="${pageContext.request.contextPath}/rentalConfirm" method="post">
    <table border="1" cellpadding="5">
    <input type="submit" value="本を借りる">
		<br>
		<br>
        <tr>
            <th>選択</th>
            <th>タイトル</th>
            <th>著者</th>
            <th>出版社</th>
            <th>ジャンル</th>
            <th>棚名</th>
        </tr>
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td><input type="checkbox" name="bookId" value="${book.bookId}"></td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.publisher}</td>
                <td>${book.genre}</td>
                <td>${book.bookArea}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    
</form>

    <a href="${pageContext.request.contextPath}/home">ホーム画面に戻る</a>
</p>