<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // 管理者のフォームから受け取るメッセージ
     request.setCharacterEncoding("UTF-8");
    String message = request.getParameter("message");

    if (message != null && !message.trim().isEmpty()) {
        // 全ユーザーに共有される application スコープに保存
        application.setAttribute("adminMessage", message);
    }

    // ホーム画面へリダイレクト
    response.sendRedirect(request.getContextPath() + "/home");
%>
