<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>貸出確認</title>
</head>
<body>
    <h2>*** 貸出確認 ***</h2>
    
      <p style="text-align: left">
      <img src="<%= request.getContextPath() %>/resources/book_character_smile.png" 
     onerror="this.src='<%= request.getContextPath() %>/resources/images/book_character_smile.png';"
     alt="ユーザー画像" width="100" height="100">

    <p>以下の本を借ります。よろしいですか？</p>

    <form action="${pageContext.request.contextPath}/rentalExecute" method="post">
        <table border="1" cellpadding="5">
            <tr>
                <th>タイトル</th>
                <th>著者</th>
                <th>出版社</th>
                <th>ジャンル</th>
                <th>棚名</th>
            </tr>
            <c:forEach var="book" items="${selectedBooks}">
                <tr>
                    <td>
                        ${book.title}
                        <input type="hidden" name="bookId" value="${book.bookId}">
                    </td>
                    <td>${book.author}</td>
                    <td>${book.publisher}</td>
                    <td>${book.genre}</td>
                    <td>${book.bookArea}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <input type="submit" value="この本を借りる">
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/rental">戻る</a>
    </p>
</body>
</html>