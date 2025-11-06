package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserEditService;

@WebServlet("/editConfirm")
public class EditConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("editPassword");
		String name = request.getParameter("editName");
		
		//　更新後の会員情報をDomainに格納
		User user = new User(loginId,null, password,name, null, 0);
		
		UserEditService editService = new UserEditService();
		boolean result = false;
		try {
			result = editService.userEditDo(user);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//　更新に成功した場合はresultにtrueが格納されている
		if(result == true) {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/editDone.jsp");
				rd.forward(request, response);
		}else {
			// 会員情報の更新に失敗した場合はエラーメッセージを用意して編集画面へ戻す
			
			request.setAttribute("editError", "メンバー情報の更新に失敗しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/edit.jsp");
				rd.forward(request, response);
		}
	}

}
