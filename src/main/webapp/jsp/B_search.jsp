<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>図書検索</title>
</head>
<body>

<h2>図書検索</h2>
<form action="${pageContext.request.contextPath}/searchBook" method="get">
    <input type="text" name="keyword" placeholder="タイトルを入力" value="${keyword}">
    <input type="submit" value="検索">
</form>

<!-- 検索語が入力された場合のみ結果を表示 -->
<c:if test="${not empty keyword}">
    <h3>検索結果</h3>

    <c:choose>
        <c:when test="${not empty bookList}">
            <table border="1">
                <tr><th>タイトル</th><th>貸出状況</th></tr>
                <c:forEach var="book" items="${bookList}">
                    <tr>
                        <td>${book.title}</td>
                        <td>
                            <c:choose>
                                <c:when test="${book.rentalStatus}">利用可能</c:when>
                                <c:otherwise>貸出中</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>該当する本は見つかりませんでした。</p>
        </c:otherwise>
    </c:choose>
</c:if>

<p><a href="${pageContext.request.contextPath}/home">ホームに戻る</a></p>

</body>
</html>
