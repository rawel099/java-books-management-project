<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン後画面</title>
    <style>
        body { margin: 0; font-family: sans-serif; }
        .container { display: flex; height: 100vh; }
        .menu {
            width: 220px;
            background: #f5f5f5;
            border-right: 1px solid #ccc;
            padding: 15px;
            box-sizing: border-box;
        }
        .content {
            flex: 1;
            padding: 20px;
            overflow-y: auto;
        }
        .menu a {
            display: block;
            margin: 10px 0;
            color: #333;
            text-decoration: none;
        }
        .menu a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- 左メニュー -->
        <div class="menu">
            <%@ include file="/WEB-INF/jsp/common/menu.jsp" %>
            <hr>
            <a href="<%= request.getContextPath() %>/home">ホーム</a>
            <a href="<%= request.getContextPath() %>/profile">プロフィール</a>
            <a href="<%= request.getContextPath() %>/settings">設定</a>
        </div>

        <!-- 右コンテンツ -->
        <div class="content">
            <jsp:include page="${pageContent}" />
        </div>
    </div>
</body>
</html>