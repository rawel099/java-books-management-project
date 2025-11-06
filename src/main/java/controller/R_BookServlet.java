package controller;

import java.io.IOException;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnection;
import dao.R_BookDAO;
import dto.R_BookDTO;


@WebServlet("/rental")
public class R_BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String sort = request.getParameter("sort");  
        List<R_BookDTO> bookList = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {
            R_BookDAO bookDao = new R_BookDAO(con);

            if ("title".equals(sort)) {
                bookList = bookDao.findAvailableBooksOrderByTitle();  // 作品名順
            } else {
                bookList = bookDao.findAvailableBooks();  // デフォルト = 登録日順
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        request.setAttribute("bookList", bookList);
        request.getRequestDispatcher("/jsp/rental_top.jsp").forward(request, response);
    }
}
