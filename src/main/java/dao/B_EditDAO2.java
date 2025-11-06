// ここは返却予定の図書一覧表示する

package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BookDTO;

public class B_EditDAO2 {
    private Connection con;

    public B_EditDAO2(Connection con) {
        this.con = con;
    }
    
	// BookDAO.java
	public List<BookDTO> findRentedBooks() throws SQLException {
	    List<BookDTO> list = new ArrayList<>();
	    String sql = "SELECT id, title, author, rental_date FROM books WHERE rental_status = false ORDER BY rental_date DESC";

	    try (PreparedStatement pstmt = con.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {
	        while (rs.next()) {
	            BookDTO book = new BookDTO();
	            book.setId(rs.getInt("id"));
	            book.setTitle(rs.getString("title"));
	            book.setAuthor(rs.getString("author"));
	            book.setRentalDate(rs.getDate("rental_date").toLocalDate());
	            list.add(book);
	        }
	    }
	    return list;
	}
}
