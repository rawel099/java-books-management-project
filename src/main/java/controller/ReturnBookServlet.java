//package controller;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import dao.BookDAO;
//import dao.DBConnection;
//import dto.BookDTO;
//
//@WebServlet("/returnBook")
////public class ReturnBookServlet extends HttpServlet {
////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        int bookId = Integer.parseInt(request.getParameter("bookId"));
////        int userId = Integer.parseInt(request.getParameter("userId"));
////
////        try (Connection con = getConnection()) {
////            BookDAO bookDao = new BookDAO(con);
////            boolean success = bookDao.returnBook(bookId, userId);
////
////            if (success) {
////                request.setAttribute("message", "返却完了しました。");
////            } else {
////                request.setAttribute("error", "返却処理に失敗しました。");
////            }
////            request.getRequestDispatcher("/jsp/book_edit.jsp").forward(request, response);
////        } catch (Exception e) {
////            throw new ServletException(e);
////        }
////    }
////
////	private Connection getConnection() {
////		return null;
////	}
////@WebServlet("/returnBook")
//public class ReturnBookServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String bookIdStr = request.getParameter("bookId");
//        int bookId = Integer.parseInt(bookIdStr);
//
//        try (Connection con = DBConnection.getConnection()) {
//            String sql = "UPDATE books " +
//                         "SET rental_status = true, rental_date = NULL, user_id = NULL " +
//                         "WHERE id = ?";
//            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
//                pstmt.setInt(1, bookId);
//                pstmt.executeUpdate();
//            }
      

        // 再び貸出ししている本の一覧を取得したあと
        
//        BookDAO dao = new BookDAO(con);
//        List<BookDTO> rentalList = dao.findRentedBooks();
//     // 返却後に一覧を再取得
//        
//        // リクエストスコープに格納
//        request.setAttribute("rentalList", rentalList);
//        request.setAttribute("message", "返却が完了しました。");
//        
//        // 返却後、一覧にリダイレクト
//        //  その後「フォワード」に変更しましょう
//        RequestDispatcher rd = request.getRequestDispatcher("/jsp/book_edit.jsp");
//        rd.forward(request, response);
        
//    } catch (SQLException e) {
//        throw new ServletException(e);
//    }
//    }
//}


//        // ★ ここで一覧を再取得
//        try (Connection con = DBConnection.getConnection()) {
//        BookDAO dao = new BookDAO(con);
//        List<BookDTO> rentalList = dao.findRentedBooks();
//
//        request.setAttribute("rentalList", rentalList);
//
//        // ★ 一覧画面にフォワード
//        RequestDispatcher rd = request.getRequestDispatcher("jsp/book_edit.jsp");
//        rd.forward(request, response);
//
//	    } catch (SQLException e) {
//	        throw new ServletException(e);
//	    }
	
            
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnection;

@WebServlet("/returnBook")
public class ReturnBookServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("bookId"));

        try (Connection con = DBConnection.getConnection()) {
            // 返却処理
            String sql = "UPDATE books " +
                         "SET rental_status = true, rental_date = NULL, user_id = NULL " +
                         "WHERE book_id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, bookId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // ★ 貸出一覧サーブレットにリダイレクト
        response.sendRedirect(request.getContextPath() + "/rentalList");
    }
}

            