package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import dao.DBConnection;
import dto.BookDTO;

/**
 * Servlet implementation class RentalListServlet
 */
@WebServlet("/rentalList")
public class RentalListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = DBConnection.getConnection()) {
            BookDAO bookDao = new BookDAO(con);
            List<BookDTO> rentedBooks = bookDao.findRentedBooks();
            request.setAttribute("rentedBooks", rentedBooks);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        request.getRequestDispatcher("/jsp/book_edit.jsp").forward(request, response);
    }
}
