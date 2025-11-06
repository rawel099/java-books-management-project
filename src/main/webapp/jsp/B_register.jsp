<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>＊＊＊メンテナンス＊＊＊</title>
</head>
<body>
		<h3>*** 図書の追加 ***</h3>
		
		<p>以下を入力して、確認ボタンを押して下さい。</p>
<form action="${pageContext.request.contextPath}/B_register" method="post">
    <table border="1">
        <tr><th>タイトル</th><td><input type="text" name="title" required></td></tr>
        <tr><th>著者</th><td><input type="text" name="author" required></td></tr>
        <tr><th>出版社</th><td><input type="text" name="publisher" required></td></tr>
        <tr><th>ジャンル</th><td><input type="text" name="genre"></td></tr>
        <tr><th>収納棚</th>
            <td>
                <select name="book_area" required>
                    <option value="A">A棚</option>
                    <option value="B">B棚</option>
                    <option value="C">C棚</option>
                    <option value="D">D棚</option>
                    <option value="E">E棚</option>
                </select>
            </td><br>
        </tr>
    </table>
    <input type="submit" value="確認へ進む">
</form>
<a href="${pageContext.request.contextPath}/rentalList">-- 戻る --</a></p> 
</body>
</html>