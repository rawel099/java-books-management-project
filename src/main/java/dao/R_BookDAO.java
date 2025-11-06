package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dto.R_BookDTO;

public class R_BookDAO {
    private Connection con;

    public R_BookDAO(Connection con) {
        this.con = con;
    }

    /**
     * 貸出可能な本の一覧を取得
     * rental_status = true（貸出可）の本だけ返す*/
     //本の登録日時順に並び替え
    
    public List<R_BookDTO> findAvailableBooks() throws SQLException {
        List<R_BookDTO> books = new ArrayList<>();
        String sql = "SELECT book_id, title, author, publisher, genre, book_area, rental_status, created_at, user_id "
                   + "FROM books WHERE rental_status = true ORDER BY created_at DESC";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // null チェックを追加
                Timestamp ts = rs.getTimestamp("created_at");
                LocalDateTime createdAt = (ts != null) ? ts.toLocalDateTime() : null;

                R_BookDTO book = new R_BookDTO(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getString("genre"),
                    rs.getString("book_area"),
                    rs.getBoolean("rental_status"),
                    createdAt,
                    rs.getString("user_id")
                );
                books.add(book);
            }
        }
        return books;
    }
    
    //作品名順に並び替え
    public List<R_BookDTO> findAvailableBooksOrderByTitle() throws SQLException {
        List<R_BookDTO> books = new ArrayList<>();
        String sql = "SELECT book_id, title, author, publisher, genre, book_area, rental_status, created_at "
                   + "FROM books WHERE rental_status = true ORDER BY title ASC";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                R_BookDTO book = new R_BookDTO(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getString("genre"),
                    rs.getString("book_area"),
                    rs.getBoolean("rental_status"),
                    rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null
                );
                	rs.getString("user_id");
                books.add(book);
            }
        }
        return books;
    }

    
    /*** 複数のIDから本の一覧を取得する*/
    public List<R_BookDTO> findBooksByIds(List<Integer> ids) throws SQLException {
        List<R_BookDTO> books = new ArrayList<>();
        if (ids == null || ids.isEmpty()) {
            return books; // 空ならそのまま返す
        }

        // プレースホルダ (?, ?, ? ...)
        String placeholders = String.join(",", java.util.Collections.nCopies(ids.size(), "?"));

        String sql = "SELECT book_id, title, author, publisher, genre, book_area, rental_status, created_at, user_id "
                   + "FROM books WHERE book_id IN (" + placeholders + ")";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (int i = 0; i < ids.size(); i++) {
                pstmt.setInt(i + 1, ids.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    R_BookDTO book = new R_BookDTO(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("genre"),
                        rs.getString("book_area"),
                        rs.getBoolean("rental_status"),
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        rs.getString("user_id")  // ★ DTOのコンストラクタに渡す
                    );
                    books.add(book);
                }
            }
        }
        return books;
    }
		

    
	public boolean updateRentalDate(int bookId, String newDate) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
				// books.rental_status と rental_date ,貸出ユーザーを更新
				public void updateRentalStatusAndDate(int bookId, boolean status, LocalDate rentalDate, String userId) throws SQLException {
				    String sql = "UPDATE books SET rental_status = ?, rental_date = ?, user_id = ? WHERE book_id = ?";
				    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				        pstmt.setBoolean(1, status);
						/*pstmt.setDate(2, java.sql.Date.valueOf(rentalDate));*/
				        pstmt.setTimestamp(2, Timestamp.valueOf(rentalDate.atStartOfDay()));
				        pstmt.setString(3, userId);
				        pstmt.setInt(4, bookId);
				        pstmt.executeUpdate();
				        
				    }
				}
}
