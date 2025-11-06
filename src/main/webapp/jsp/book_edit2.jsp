<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>メンテナンス画面</title>
		<link rel= "stylesheet" type="text/css" href="/mini2App/css/stylsheet.css"/>
</head>

<body>
	<h1>** メンテナンス **</h1>
	<p style="text-align: right">お疲れ様です。${user.name} 様</p>
	
	
<h3>貸出中の書籍一覧</h3>
  
<c:if test="${empty rentedBooks}">
  <p>現在、貸出中の書籍はありません。</p>
</c:if>

<c:if test="${not empty rentedBooks}">
  <table border="1" style="width:100%; text-align:center;">
    <tr>
      <th>本ID</th>
      <th>タイトル</th>
      <th>借りているユーザー</th>
      <th>貸出期限</th>
      <th>返却</th>
      <th>貸出期限変更</th>
    </tr>
    <c:forEach var="book" items="${rentedBooks}">
      <tr>
        <td>${book.Id}</td>
        <td>${book.title}</td>
        <td>${book.userId}</td>
        <td>${book.rentalDate}</td>
        <td>
          <form action="${pageContext.request.contextPath}/returnBook" method="post">
            <input type="hidden" name="bookId" value="${book.Id}" />
            <input type="hidden" name="userId" value="${book.userId}" />
            <button type="submit">返却完了</button>
          </form>
        </td>
        <td>
          <form action="${pageContext.request.contextPath}/updateRentalDate" method="post">
            <input type="hidden" name="bookId" value="${book.Id}" />
            <input type="date" name="newDate" />
            <button type="submit">変更</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
</c:if>
	<br>
	<br>
	<br>
	<a href="/mini2App/jsp/B_register.jsp">*** 図書の追加はこちら ***</a></p> 
	<br>
	<a href="${pageContext.request.contextPath}/jsp/book_edit2.jsp">*** 図書の編集はこちら ***</a></p>
	<br>
		<p><a href="/miniApp/jsp/home.jsp">ホーム画面に戻る</a></p>
		<p style="text-align: right"><a href="/mini2App/logout">ログアウト</a></p> 
</body>

</html>


