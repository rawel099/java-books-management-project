//package controller;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import dao.DBConnection;
//import dao.R_BookDAO;
//import dto.R_BookDTO;
//
//@WebServlet("/rentalConfirm")
//public class RentalConfirmServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        // 選択された本のID（複数チェックボックス）
//        String[] selectedBookIds = request.getParameterValues("bookId");
//        if (selectedBookIds == null || selectedBookIds.length == 0) {
//            request.setAttribute("error", "本が選択されていません。");
//            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
//            return;
//        }
//
//        List<R_BookDTO> selectedBooks = new ArrayList<>();
//
//        try (Connection con = DBConnection.getConnection()) {
//            R_BookDAO bookDao = new R_BookDAO(con);
//
//            for (String idStr : selectedBookIds) {
//                int id = Integer.parseInt(idStr);
//                R_BookDTO book = bookDao.findBookById(id);
//                if (book != null) {
//                    selectedBooks.add(book);
//                }
//            }
//
//        } catch (SQLException e) {
//            throw new ServletException(e);
//        }
//
//        // JSPに渡す
//        request.setAttribute("selectedBooks", selectedBooks);
//        request.getRequestDispatcher("/jsp/rental_confirm.jsp").forward(request, response);
//    }
//}


package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnection;
import dao.R_BookDAO;
import dto.R_BookDTO;

@WebServlet("/rentalConfirm")
public class RentalConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 選択された本のID（複数チェックボックス）
        String[] selectedBookIds = request.getParameterValues("bookId");
        if (selectedBookIds == null || selectedBookIds.length == 0) {
            request.setAttribute("error", "本が選択されていません。");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }

        // String[] → List<Integer> に変換
        List<Integer> ids = Arrays.stream(selectedBookIds)
                                  .map(Integer::parseInt)
                                  .collect(Collectors.toList());

        try (Connection con = DBConnection.getConnection()) {
            R_BookDAO bookDao = new R_BookDAO(con);

            // DAOの一括取得メソッドを使う
            List<R_BookDTO> selectedBooks = bookDao.findBooksByIds(ids);

            // JSPに渡す
            request.setAttribute("selectedBooks", selectedBooks);
            request.getRequestDispatcher("/jsp/rental_confirm.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
