<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>返却予定リスト</title>
</head>
<body>
<h2>** メンテナンス **</h2>
	<p style="text-align: left">お疲れ様です。${user.name} 様</p>

<h3>貸出中の本一覧</h3>

    <p style="text-align: left">
      <img src="<%= request.getContextPath() %>/resources/book_booktrack.png" 
     onerror="this.src='<%= request.getContextPath() %>/resources/images/book_booktrack.png';"
     alt="ユーザー画像" width="100" height="100">
<c:if test="${empty rentedBooks}">
    <p><h2>** 現在貸し出されている本はありません。**</h2></p>
</c:if>
 
<c:if test="${not empty rentedBooks}">
	
	<table border="1">
	    
    <tr>
        <th>ID</th>
        <th>タイトル</th>
        <th>著者</th>
        <th>収納棚</th>
        <th>貸出日</th>
        <th>返却予定日</th>
        <th>利用者ID</th>
        <th>操作</th>
    </tr>
    <c:forEach var="book" items="${rentedBooks}">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.bookArea}</td>
            <td>${book.rentalDate}</td>
            <td>${book.returnDueDate}</td>
            <td>${book.userId}</td>
            <td>
           
                <form action="/mini2App/returnBook" method="post">
                    <input type="hidden" name="bookId" value="${book.id}">
                    <input type="submit" value="返却"
                    onclick="return confirm('返却して宜しいですか？');">
                </form>
            </td>
        </tr>
    </c:forEach>
 </table>
</c:if>

	<br>
	<br>
	<br>
	
	<a href="/mini2App/jsp/B_register.jsp">*** 新規図書の追加はこちら ***</a></p> 
	<br>
	<a href="${pageContext.request.contextPath}/jsp/edit_book.jsp">*** 図書の編集はこちら ***</a></p> 
	
	<br>
		<p><a href="${pageContext.request.contextPath}/home">ホーム画面に戻る</a></p>
		<p style="text-align: right"><a href="/mini2App/logout">ログアウト</a></p> 
</body>

</html>