package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import dto.BookDTO;

@WebServlet("/registerBook")
public class RegisterBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // DTOに詰める
        BookDTO book = new BookDTO();
        book.setTitle(request.getParameter("title"));
        book.setAuthor(request.getParameter("author"));
        book.setPublisher(request.getParameter("publisher"));
        book.setGenre(request.getParameter("genre"));
        book.setBookArea(request.getParameter("book_area"));//, 
        //book.setRentalStatus(request.getParameter("rental_status"));
        book.setRentalDate(request.getParameter("rental_date"));

        // DAO呼び出し
        BookDAO dao = new BookDAO(null);
        String errorMessage = dao.insertBook(book);

        if (errorMessage == null) {
            // 成功時
            //response.sendRedirect(request.getContextPath() + "/jsp/B_registerDone.jsp");
            
        	// 登録した本の情報をリクエストに渡す
            request.setAttribute("registeredBook", book);

            // 完了画面へフォワード
            RequestDispatcher dispatcher =
                request.getRequestDispatcher("/jsp/registerComplete.jsp");
            dispatcher.forward(request, response);
        	
        } else {

        	  // 失敗時はエラーメッセージを渡す
            request.setAttribute("message", "登録に失敗しました。");
            RequestDispatcher dispatcher =
                request.getRequestDispatcher("/jsp/B_registerError.jsp");
            dispatcher.forward(request, response);
        }
    }
}