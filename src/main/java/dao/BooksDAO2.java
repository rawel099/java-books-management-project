package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO2 {
    private String url = "jdbc:postgresql://localhost:5433/postgres";
    private String user = "postgres";
    private String password = "password";

    
    public List<Book> findAllBooksWithBorrower() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT b.book_id, b.title, u.name AS borrower_name " +
                     "FROM books b LEFT JOIN users u ON b.borrowed_by = u.user_id";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setBorrowerName(rs.getString("borrower_name")); // 借りていない場合は null
                list.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


	public List<Book> findAvailableBooks() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}