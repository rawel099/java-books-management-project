package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO2 {
    private Connection con;

    // Connectionを受け取るコンストラクタ
    public UserDAO2(Connection con) {
        this.con = con;
    }
    

    /**
     * 貸出冊数を増やす（最大3冊まで）
     */
    public boolean incrementRentalBooks(int loginId, int rental_books) throws SQLException {
        // 現在の貸出冊数を取得
        int current = getRentalBooks(loginId);

        if (current + rental_books > 3) {
            // 3冊を超える場合は処理せず false を返す
            return false;
        }

        String sql = "UPDATE users SET rental_books = rental_books + ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, rental_books);
            pstmt.setInt(2, loginId);
            pstmt.executeUpdate();
        }
        return true;
    }

    /**
     * 貸出冊数を減らす（0未満にならないように制御）
     */
    public void decrementRentalBooks(int loginId, int count) throws SQLException {
        String sql = "UPDATE users " +
                     "SET rental_books = CASE " +
                     "    WHEN rental_books - ? < 0 THEN 0 " +
                     "    ELSE rental_books - ? " +
                     "END " +
                     "WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, count);
            pstmt.setInt(2, count);
            pstmt.setInt(3, loginId);
            pstmt.executeUpdate();
        }
    }

    /**
     * 現在の貸出冊数を取得
     */
    public int getRentalBooks(int loginId) throws SQLException {
        String sql = "SELECT rental_books FROM users WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, loginId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("rental_books");
                }
            }
        }
        return 0;
    }
}