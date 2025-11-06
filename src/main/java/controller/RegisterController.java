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
import service.UserRegisterService;
import validation.Validation;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//register.jspへフォワード
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/register.jsp");
			rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータを取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		
		//バリデーションで入力値チェック
		Validation validation = new Validation();
		validation.isBlank("ユーザーID", loginId);
		validation.isBlank("パスワード", password);
		validation.isBlank("お名前", name);
		validation.length("ユーザーID", loginId, 1, 8);
		validation.length("パスワード", password, 2, 10);
		validation.length("お名前", name, 1, 10);
		
		//入力値エラーがあった場合
		if(validation.hasErrorMsg()) {
			request.setAttribute("errorMsg", validation.getErrorMsList());
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/register.jsp");
				rd .forward(request, response);
		}
		
		//ユーザー情報をDomainに格納
		User user = new User(loginId,null, password,name, null, 0);
		//新規登録したいユーザーがDBに既に存在するかチェック
		UserRegisterService registerService = new UserRegisterService();
			boolean result = false;
			try {
				result = registerService.userEntryConfirm(user);
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
			//ユーザーが存在しない場合はresultにtrueが格納されている
			if(result == true) {
				//リクエストスコープにDomainを格納してフォワード
				request.setAttribute("user", user);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/registerConfirm.jsp");
					rd.forward(request, response);
			}else {
				//既にユーザーが存在する場合はエラーメッセージを用意して新規登録画面へ戻す
				validation.addErrorMsg("入力いただいたID「"+ loginId + "」は既に使われています");
				request.setAttribute("errorMsg", validation.getErrorMsList());
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/register.jsp");
					rd.forward(request, response);
			}
	
	}

}
