package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.R_BookDAO;

@WebServlet("/updateRentalDate")
public class UpdateRentalDateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String newDate = request.getParameter("newDate"); // yyyy-MM-dd

        try (Connection con = getConnection()) {
            R_BookDAO bookDao = new R_BookDAO(con);
            boolean success = bookDao.updateRentalDate(bookId, newDate);

            if (success) {
                request.setAttribute("message", "貸出期限を変更しました。");
            } else {
                request.setAttribute("error", "期限変更に失敗しました。");
            }
            request.getRequestDispatcher("/jsp/book_edit.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

	private Connection getConnection() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
