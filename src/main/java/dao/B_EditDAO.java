// ここは返却予定の図書一覧表示する

package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dto.B_EditDTO;

public class B_EditDAO {
    private Connection con;

    public B_EditDAO(Connection con) {
        this.con = con;
    }

    public B_EditDAO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
     * 返却予定本の一覧を取得
     * rental_status = False（貸出中）の本だけ返す*/
     //本の登録日時順に並び替え
    
    public List<B_EditDTO> findAvailableBooks() throws SQLException {
        List<B_EditDTO> books = new ArrayList<>();
        String sql = "SELECT book_id, title, author, publisher, genre, book_area, rental_status, created_at "
                   + "FROM books WHERE rental_status = false ORDER BY created_at DESC";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // null チェックを追加
                Timestamp ts = rs.getTimestamp("created_at");
                LocalDateTime createdAt = (ts != null) ? ts.toLocalDateTime() : null;

                B_EditDTO book = new B_EditDTO(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getString("genre"),
                    rs.getString("book_area"),
                    rs.getBoolean("rental_status"),
                    createdAt
                );
                books.add(book);
            }
        }
        return books;
    }
    
    //作品名順に並び替え
    public List<B_EditDTO> findAvailableBooksOrderByTitle() throws SQLException {
        List<B_EditDTO> books = new ArrayList<>();
        String sql = "SELECT book_id, title, author, publisher, genre, book_area, rental_status, created_at "
                   + "FROM books WHERE rental_status = false ORDER BY title ASC";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	B_EditDTO book = new B_EditDTO(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getString("genre"),
                    rs.getString("book_area"),
                    rs.getBoolean("rental_status"),
                    rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null
                );
                books.add(book);
            }
        }
        return books;
    }
    public B_EditDTO findBookById(int id) throws SQLException {
        String sql = "SELECT book_id, title, author, publisher, genre, book_area, rental_status, created_at "
                   + "FROM books WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new B_EditDTO(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("genre"),
                        rs.getString("book_area"),
                        rs.getBoolean("rental_status"),
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null
                    );
                }
            }
        }
        return null;
    }
	public void update(B_EditDTO book) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
