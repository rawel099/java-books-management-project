package controller;
		//記述内でつかう外部機能をインポート
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
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.DBConnection;
import domain.User;
import dto.BookDTO;
import service.UserLoginService;

@WebServlet("/login") 	//アノテーション。loginを参照先として設定
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//　doGetメソッドによって最初にユーザーに表示するlogin.jspへフォワード
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
			rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータを取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		
		//UserLoginService のloginCheckメソッドにパラメータを渡す
		UserLoginService loginService = new UserLoginService(); //UserLoginService をnewでインスタンス作成
		User user = loginService.loginCheck(loginId, password); 
		//上記で作成したインスタンスで入力されたloginIdとパスワードをチェックする

		if (user != null) {
		    HttpSession session = request.getSession();
		    session.setAttribute("user", user);

		    // ★ ここで一覧を取得
		    try (Connection con = DBConnection.getConnection()) {
		        BookDAO dao = new BookDAO(con);
		        List<BookDTO> bookList = dao.findAllBooks();
		        request.setAttribute("bookList", bookList);
		    } catch (SQLException e) {
		        throw new ServletException(e);
		    }
		    System.out.println("Forwarding to rental_top.jsp");
		    // 直接 JSP へ
		    request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
		    
		}
	}
}
