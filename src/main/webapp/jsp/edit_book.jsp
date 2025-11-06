<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>本の編集</h2>
<form action="${pageContext.request.contextPath}/editBook" method="post">
    <table border="1">
       <tr>
    <th>ID</th>
    <td>
        <!-- 表示用（編集不可） -->
        <input type="text" value="${book.id}" readonly>
        <!-- 更新用（DBに渡す） -->
        <input type="hidden" name="id" value="${book.id}">
    </td>
</tr>
        <tr>
            <th>タイトル</th>
            <td>
                <input type="text" name="title" value="${book.title}">
            </td>
        </tr>
        <tr>
            <th>著者</th>
            <td>
                <input type="text" name="author" value="${book.author}">
            </td>
        </tr>
        <tr>
            <th>収納棚</th>
            <td>
                <select name="bookArea" required>
                    <option value="A" <c:if test="${book.bookArea == 'A'}">selected</c:if>>A棚</option>
                    <option value="B" <c:if test="${book.bookArea == 'B'}">selected</c:if>>B棚</option>
                    <option value="C" <c:if test="${book.bookArea == 'C'}">selected</c:if>>C棚</option>
                    <option value="D" <c:if test="${book.bookArea == 'D'}">selected</c:if>>D棚</option>
                    <option value="E" <c:if test="${book.bookArea == 'E'}">selected</c:if>>E棚</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>貸出可否</th>
            <td>
                <select name="rentalStatus">
                    <option value="true"  <c:if test="${book.rentalStatus}">selected</c:if>>貸出可</option>
                    <option value="false" <c:if test="${!book.rentalStatus}">selected</c:if>>貸出中</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>貸出日</th>
            <td>
                <input type="date" name="rentalDate" value="${book.rentalDate}">
            </td>
        </tr>
        <tr>
            <th>利用者ID</th>
            <td>
                <input type="text" name="userId" value="${book.userId}">
            </td>
        </tr>
    </table>
    <input type="submit" value="更新する"
           onclick="return confirm('ID:${book.id} の更新を行いますか？');">
</form>
<p><a href="${pageContext.request.contextPath}/home">書籍一覧へ戻る</a></p>
</body>
</html>