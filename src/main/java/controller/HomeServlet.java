package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import dao.DBConnection;
import dto.BookDTO;

//Home画面に書籍一覧を表示
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            try (Connection con = DBConnection.getConnection()) {
                BookDAO dao = new BookDAO(con);
                List<BookDTO> bookList = dao.findAllBooks();

                request.setAttribute("bookList", bookList);

                RequestDispatcher rd = request.getRequestDispatcher("/jsp/home.jsp");
                rd.forward(request, response);
            } catch (SQLException e) {
                throw new ServletException(e);
            }
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }