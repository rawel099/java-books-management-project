package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import validation.Validation;

@WebServlet("/edit")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// edit.jspへフォワード
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/edit.jsp");
			rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		
		//リクエストパラメータを取得
		String editPassword = request.getParameter("editPassword");
		String editName= request.getParameter("editName");
		
		//バリデーション
		Validation validation = new Validation();
		validation.isBlank("パスワード", editPassword);
		validation.isBlank("お名前", editName);
		validation.length("パスワード", editPassword, 2, 10);
		validation.length("お名前", editName, 1, 10);
		
		//入力値エラーがあった場合
		if(validation.hasErrorMsg()) {
			request.setAttribute("errorMsg", validation.getErrorMsList());
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/edit.jsp");
			rd.forward(request, response);
		}else {
			//変更後の値をリクエストスコープへ保存して確認画面へ
			request.setAttribute("editName", editName);
			request.setAttribute("editPassword", editPassword);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/editConfirm.jsp");
			rd.forward(request, response);
		}
	}
}