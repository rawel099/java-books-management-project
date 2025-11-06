<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>☆★　Home画面　★☆</title>
	<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/css/stylesheet.css"/>
</head>
	<body>
	<div class="container">
	
			<%@ page import="java.util.Calendar, java.text.SimpleDateFormat" %>
<%
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 (E)", java.util.Locale.JAPANESE);
    String today = sdf.format(cal.getTime());

    int month = cal.get(Calendar.MONTH) + 1;
    String monthImg = "/resources/images/month/" + String.format("%02d", month) + ".png";
%>

<div class="date">本日の日付：<%= today %></div>
<div class="month-image">
    <img src="<%= request.getContextPath() + monthImg %>" alt="月別イメージ" width="180">
</div>
	
		<h1>** ホーム画面 **</h1>
		<p style="text-align: left">${user.name} 様　　ログイン中</p>
		<p style="text-align: center"><a href="/mini2App/logout">ログアウト</a></p>

		
		
<c:choose>
  <c:when test="${user.id_check == true}">
  
  
      <h3>☆★　管理者専用ページです　★☆</h3>
      
      <p>ユーザー画面に表示したい情報を入力できます。</p>
      
        <!-- 管理者からメッセージ送信用フォーム -->
      <p style="text-align: left">
      <img src="<%= request.getContextPath() %>/resources/book_renrakuchou.png" 
     onerror="this.src='<%= request.getContextPath() %>/resources/images/book_renrakuchou.png';"
     alt="ユーザー画像" width="100" height="100">
        
  		<form action="${pageContext.request.contextPath}/jsp/postMessage.jsp" method="post">
    	<textarea name="message" rows="5" cols="80"
      placeholder="メッセージを入力してください"></textarea><br>
    	<button type="submit">送信</button>
  		</form>
      
      <h3>メンテナンス</h3>
      <p>メンテナンス画面へ飛びます。下のリンクをクリックしてください。</p>
	  <p style="text-align: left"><a href="${pageContext.request.contextPath}/rentalList">** メンテナンス画面 **</a></p>
  		
  </c:when>
<c:otherwise>

      <h2>いらっしゃいませ。</h2>
      <p>こちらのページから来館者の方向けの情報を表示します。</p>
      
        <!-- 管理者メッセージ表示枠 -->
  	<c:if test="${not empty applicationScope.adminMessage}">
  	
    <div style="border:2px solid #000; padding:10px; margin:10px; background-color:#f9f9f9;">
    <!--  <div class="message-box"> -->
      <strong>お知らせ:</strong><br>
      ${applicationScope.adminMessage}
    </div>
  </c:if>
      <!--  p style="text-align: center"><h3>☆★　書籍貸出画面 ★☆</h3></p>-->
      <br>
      <!-- <p style="text-align: left">貸出ページへ飛びます。リンクをクリックしてください。</p> -->
      <p style="text-align: center">
      <img src="<%= request.getContextPath() %>/resources/school_tosyokan_kakari_girl.png" 
     onerror="this.src='<%= request.getContextPath() %>/resources/images/school_tosyokan_kakari_girl.png';"
     alt="ユーザー画像" width="100" height="100">
      
		<p style="text-align: center">
		<a href="${pageContext.request.contextPath}/rental">＊＊書籍貸出はこちらから＊＊</a></p>
		<br>
		    <p style="text-align: center">
      <img src="<%= request.getContextPath() %>/resources/annai_monitor_apron_man.png" 
     onerror="this.src='<%= request.getContextPath() %>/resources/images/annai_monitor_apron_man.png';"
     alt="ユーザー画像" width="100" height="100">
		<p style="text-align: center">
		<a href="${pageContext.request.contextPath}/searchBook">＊＊書籍検索はこちらから＊＊</a></p>
  </c:otherwise>
</c:choose>

	
<hr>

<!-- 📚 本の一覧表示（全ユーザー共通） -->
<h2>▲▽　蔵書一覧　▽▲</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>タイトル</th>
        <th>著者</th>
        <th>収納棚</th>
        <th>貸出</th>
        <th>貸出日</th>
        <th>返却予定日</th>
        <c:if test="${user.id_check == true}">
	    	<th>編集</th>
		</c:if>    
    </tr>
    <c:forEach var="book" items="${bookList}">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.bookArea}</td>
            <td><c:choose>
                <c:when test="${book.rentalStatus}">〇</c:when>
                <c:otherwise>×</c:otherwise>
            </c:choose></td>
            <td>${book.rentalDate}</td>
            <td>${book.returnDueDate}</td>
           	<c:if test="${user.id_check}">
    		<td>           
            <form action="${pageContext.request.contextPath}/editBook" method="get">
	    <input type="hidden" name="id" value="${book.id}">
	    <input type="submit" value="編集">
</form>
        </form>
    </td>
</c:if>
        </tr>
    </c:forEach>
</table><br>
 <c:if test="${user.id_check == false}">
	    <p>☆★　ユーザー情報の変更はこちらから　★☆</p>
		<br>
		<p><a href="/mini2App/edit">メンバー情報の編集</a></p>
		<p><a href="/mini2App/delete">退会する方はこちら</a></p>
		</c:if>
		<br>
		<p style="text-align: left"><a href="/mini2App/logout">ログアウト</a></p>
	</div>
</body>
</html>