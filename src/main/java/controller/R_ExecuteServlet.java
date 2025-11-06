package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBConnection;
import dao.R_BookDAO;
import domain.User;

@WebServlet("/rentalExecute")
public class R_ExecuteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private String loginUserId;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // hidden から渡された bookId（複数）
        String[] bookIds = request.getParameterValues("bookId");

        if (bookIds == null || bookIds.length == 0) {
            request.setAttribute("error", "貸出対象の本が選択されていません。");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            R_BookDAO bookDao = new R_BookDAO(con);

            // トランザクション開始
            con.setAutoCommit(false);
            
            for (String idStr : bookIds) {
                int bookId = Integer.parseInt(idStr);

              //追加　でloginId＝ loginUserIdとして保存
                
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                loginUserId = user.getLoginId();
                
                // booksテーブルの貸出ステータスを更新 + rental_date=今日 + user_id を登録
                bookDao.updateRentalStatusAndDate(bookId, false, LocalDate.now(), loginUserId);
                // 2. usersテーブルの貸出冊数を +1（複数冊ならその冊数ぶん加算）
                //UserDao.incrementRentalBooks(loginUserId, bookIds.length);
                
            }
            con.commit(); // コミット

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("貸出処理に失敗しました", e);
        }
        // 完了画面へリダイレクト（またはホームへ）
        response.sendRedirect(request.getContextPath() + "/jsp/rental_complete.jsp");
//      //login_idをセッションから受け取る
//        HttpSession session = request.getSession();
//        String sessionUserId = (String) session.getAttribute("login_id");
    }
    
}