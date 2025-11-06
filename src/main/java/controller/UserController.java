package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {

	        // 値をセット
	        request.setAttribute("message", "コントローラからJSPへ渡した文字列です");

	        // JSPへフォワード
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/home.jsp");
	        dispatcher.forward(request, response);
	    }
	}
