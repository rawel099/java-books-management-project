<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>更新完了</title>
</head>
<body>
    <h2>${message}</h2>

    <p>更新した内容：</p>
    <ul>
        <li>ID: ${updateBook2.book_id}</li>
        <li>タイトル: ${updateBook2.title}</li>
        <li>著者: ${updateBook2.author}</li>
        <li>収納棚: ${updateBook2.bookArea}</li>
        <li>貸出可否: 
            <c:choose>
                <c:when test="${updaupdateBook2tedBook2.rentalStatus}">貸出可</c:when>
                <c:otherwise>貸出中</c:otherwise>
            </c:choose>
        </li>
        <li>貸出日: ${updateBook2.rentalDate}</li>
        <li>利用者ID: ${updateBook2.userId}</li>
    </ul>

    <!-- B_editDone.jsp -->
    <h2>更新が完了しました！</h2>
    <p><a href="${pageContext.request.contextPath}/home">ホームに戻る</a></p>
</body>
</html>
</body>
</html>