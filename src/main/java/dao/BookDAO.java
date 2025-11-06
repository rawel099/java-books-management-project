package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.BookDTO;


//*** 本の登録機能 ***
public class BookDAO extends BaseDAO{
	private Connection con;

    public BookDAO(Connection con) {
        this.con = con;
    }
	
	public BookDAO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public String insertBook(BookDTO book) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO books (title, author, publisher, genre, book_area, rental_status, rental_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getPublisher());
            ps.setString(4, book.getGenre());
            ps.setString(5, book.getBookArea());
            ps.setBoolean(6, true);
            ps.setString(7, book.getRentalDate());

            int result = ps.executeUpdate();
            if (result > 0) {
                return null; // 成功時はエラーメッセージなし
            } else {
                return "書籍の登録に失敗しました。"; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "エラー: " + e.getMessage(); // DBのエラーメッセージを返す
        }
    }
	//全ての図書を取得
	public List<BookDTO> findAllBooks() throws SQLException {
	    List<BookDTO> list = new ArrayList<>();
	    String sql = "SELECT book_id, title, author, book_area, rental_status,rental_date, (CAST(rental_date AS DATE) + INTERVAL '14 day'), user_id " +
	                 "FROM books ORDER BY book_id ASC";

	    try (PreparedStatement pstmt = con.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {
	        while (rs.next()) {
	            BookDTO book = new BookDTO();
	            book.setId(rs.getInt("book_id"));
	            book.setTitle(rs.getString("title"));
	            book.setAuthor(rs.getString("author"));
	            book.setBookArea(rs.getString("book_area"));
	            book.setRentalStatus(rs.getBoolean("rental_status"));
	            book.setRentalDate(rs.getString("rental_date"));

	            if (rs.getDate("rental_date") != null) {
	                LocalDate rentalDate = rs.getDate("rental_date").toLocalDate();
	                book.setRentalDate(rentalDate);
	                book.setReturnDueDate(rentalDate.plusDays(14)); // ★ 返却予定日
	            }
	            book.setUserId(rs.getString("user_id"));
	            list.add(book);
	        }
	    }
	    return list;
	}

	
	//貸出中の図書一覧を取得
	public List<BookDTO> findRentedBooks() throws SQLException {
	    List<BookDTO> list = new ArrayList<>();
	    String sql = "SELECT book_id, title, author, book_area, rental_date, (CAST(rental_date AS DATE) + INTERVAL '14 day'), user_id " +
	                 "FROM books WHERE rental_status = false " +
	                 "ORDER BY book_id ASC";

	    try (PreparedStatement pstmt = con.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {
	        while (rs.next()) {
	            BookDTO book = new BookDTO();
	            book.setId(rs.getInt("book_id"));
	            book.setTitle(rs.getString("title"));
	            book.setAuthor(rs.getString("author"));
	            book.setBookArea(rs.getString("book_area"));
	            // ★ rental_date を LocalDate で取得
	            LocalDate rentalDate = rs.getObject("rental_date", LocalDate.class);
	            if (rentalDate != null) {
	                book.setRentalDate(rentalDate);
	                book.setReturnDueDate(rentalDate.plusDays(14)); // 返却予定日
	            }
	            book.setUserId(rs.getString("user_id"));
	            list.add(book);
	        }
	    }
	    return list;
		}
//
	public BookDTO findById(int bookId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	public List<BookDTO> searchByTitle(String keyword) {
	    List<BookDTO> list = new ArrayList<>();

	    String sql = "SELECT book_id, title, rental_status FROM books WHERE title ILIKE ?";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, "%" + keyword + "%");  // ← 1文字でもOK！

	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            BookDTO book = new BookDTO();
	            book.setId(rs.getInt("book_id"));
	            book.setTitle(rs.getString("title"));
	            book.setRentalStatus(rs.getBoolean("rental_status"));
	            list.add(book);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
		// 更新処理（全カラム対応）
		public void updateBook2(BookDTO book) throws SQLException {
		    String sql = "UPDATE books SET book_id=?, title=?, author=?, book_area=?, rental_status=?, rental_date=?, user_id=? WHERE id=?";
		    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		    	pstmt.setInt(1, book.getId());
		        pstmt.setString(2, book.getTitle());
		        pstmt.setString(3, book.getAuthor());
		        pstmt.setString(4, book.getBookArea());
		        pstmt.setBoolean(5, book.isRentalStatus());

		        if (book.getRentalDate() != null) {
		            pstmt.setDate(6, java.sql.Date.valueOf(book.getRentalDate()));
		        } else {
		            pstmt.setNull(6, java.sql.Types.DATE);
		        }

		        pstmt.setString(7, book.getUserId());
		        //pstmt.setInt(8, book.getId());

		        pstmt.executeUpdate();
		    }
		
		}

		public BookDTO findBookById(int id) throws SQLException {
		    String sql = "SELECT book_id, title, author, book_area, rental_status, rental_date, user_id " +
		                 "FROM books WHERE book_id = ?";
		    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		        pstmt.setInt(1, id);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                BookDTO book = new BookDTO();
		                book.setId(rs.getInt("book_id"));
		                book.setTitle(rs.getString("title"));
		                book.setAuthor(rs.getString("author"));
		                book.setBookArea(rs.getString("book_area"));
		                book.setRentalStatus(rs.getBoolean("rental_status"));
		                book.setUserId(rs.getString("user_id"));
		                if (rs.getDate("rental_date") != null) {
		                    book.setRentalDate(rs.getDate("rental_date").toLocalDate());
		                }
		                return book;
		            }
		        }
		    }
		    return null;
		}
}