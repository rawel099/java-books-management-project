package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PostMessage")
public class PostMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String message = request.getParameter("message");

        if (message != null && !message.trim().isEmpty()) {
            // 全ユーザーに共有したい場合（applicationスコープ）
            getServletContext().setAttribute("adminMessage", message);
        }

        // home.jsp へ戻る
        response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
    }
}
