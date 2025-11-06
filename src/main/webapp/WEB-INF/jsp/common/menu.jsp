<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
<%@ page import="java.util.Calendar, java.text.SimpleDateFormat" %>
<%
    // 今日の日付を取得
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 (E)", java.util.Locale.JAPANESE);
    String today = sdf.format(cal.getTime());

    // 月ごとの画像ファイル名を生成
    int month = cal.get(Calendar.MONTH) + 1; // 1月～12月
    String monthImg = "/resources/images/month/" + String.format("%02d", month) + ".png";
%>

<div class="menu">
    <div class="date">
        <strong><%= today %></strong>
    </div>
    <div class="month-image">
        <img src="<%= request.getContextPath() + monthImg %>" 
             alt="月別イメージ" width="150">
    </div>
</div>  -->

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

