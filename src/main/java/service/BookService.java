package service;

import dao.BookDAO;
import dto.BookDTO;

public class BookService {

    public String registerBook(BookDTO book) {
        BookDAO dao = new BookDAO();
        return dao.insertBook(book);
    }
}