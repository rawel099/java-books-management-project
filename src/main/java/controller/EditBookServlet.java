package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import dao.DBConnection;
import dto.BookDTO;

@WebServlet("/editBook")
public class EditBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            throw new ServletException("本のIDが指定されていません。");
        }
        int bookId = Integer.parseInt(idStr);

        try (Connection con = DBConnection.getConnection()) {
            BookDAO dao = new BookDAO(con);
            BookDTO book = dao.findBookById(bookId); // ← 本を取得するDAOメソッドが必要

            if (book == null) {
                throw new ServletException("指定された本が存在しません。ID: " + bookId);
            }
            

            request.setAttribute("book", book);
            request.getRequestDispatcher("/jsp/edit_book.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String bookArea = request.getParameter("bookArea");
        String rentalStatus = request.getParameter("rentalStatus");
        String rentalDate = request.getParameter("rentalDate");
        String userId = request.getParameter("userId");
        //int id = Integer.parseInt (request.getParameter("Id"));

        try (Connection con = DBConnection.getConnection()) {
            BookDAO dao = new BookDAO(con);
            BookDTO dto = new BookDTO();
            dao.updateBook2(dto);
            //BookDTO型に詰めて　その後渡す

            response.sendRedirect(request.getContextPath() + "/jsp/B_editDone.jsp");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

