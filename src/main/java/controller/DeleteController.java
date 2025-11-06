package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserDeleteService;

@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// deleteConfirm.Jsp　へフォワード
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/deleteConfirm.jsp");
			rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		String loginId = request.getParameter("deleteLoginId");
		
		//削除したユーザー情報のloginIdをdomainに格納
		User user = new User(loginId, null, null, null, null, 0);
		UserDeleteService deleteService = new UserDeleteService();
		boolean result = false;
		try {
			result = deleteService.userDeleteDo(user);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//削除が成功した場合はresultにtrueが格納されている
		if(result == true) {
			//セッションの破棄
			HttpSession session = request.getSession();
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/deleteDone.jsp");
				rd.forward(request, response);
		}else {
			//削除に失敗した場合、エラーメッセージを用意して削除確認画面へ戻す
			request.setAttribute("deleteError", "登録情報の削除に失敗しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/deleteConfirm.jsp");
				rd.forward(request, response);
		}
	}

}
