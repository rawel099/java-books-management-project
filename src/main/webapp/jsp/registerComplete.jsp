<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<h2 style="color:blue;">登録が完了致しました。</h2>

<c:if test="${registeredBook != null}">
    <p>タイトル：${registeredBook.title}</p>
    <p>著者：${registeredBook.author}</p>
    <p>出版社：${registeredBook.publisher}</p>
    <p>ジャンル：${registeredBook.genre}</p>
    <p>保管エリア：${registeredBook.bookArea}</p>
</c:if>

<p style="text-align: center">
    <a href="${pageContext.request.contextPath}/jsp/B_register.jsp">登録画面へ戻る</a>
</p>
<p style="text-align: center">
    <a href="/mini2App/jsp/book_edit.jsp">メンテナンス画面へ戻る</a>
</p>