package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/B_register")
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 入力データ取得
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String genre = request.getParameter("genre");
        String bookArea = request.getParameter("book_area");

        // JSPに渡す
        request.setAttribute("title", title);
        request.setAttribute("author", author);
        request.setAttribute("publisher", publisher);
        request.setAttribute("genre", genre);
        request.setAttribute("book_area", bookArea);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/B_registerConfirm.jsp");
        dispatcher.forward(request, response);
    }
}
