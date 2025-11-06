package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import dto.BookDTO;

/**
 * Servlet implementation class SearchBookServlet
 */
@WebServlet("/searchBook")
public class SearchBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");

        List<BookDTO> list = new ArrayList<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            BookDAO dao = new BookDAO();
            list = dao.searchByTitle(keyword);
        }

        request.setAttribute("keyword", keyword);  // JSP表示用
        request.setAttribute("bookList", list);
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/B_search.jsp");
        rd.forward(request, response);
    }
}

    

