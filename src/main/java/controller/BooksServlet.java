//package controller;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import dao.Book2;
//import dao.BooksDAO2;
//
//public class BooksServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        BooksDAO2 dao = new BooksDAO2();
//        List<Book> availableBooks = dao.findAvailableBooks();
//
//        request.setAttribute("booksList", availableBooks);
//        RequestDispatcher rd = request.getRequestDispatcher("/books.jsp");
//        rd.forward(request, response);
//    }
//}