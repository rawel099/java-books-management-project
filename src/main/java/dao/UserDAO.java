package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDTO;

public class UserDAO extends BaseDAO{
	
//	public class BaseDAO {

	    final String DB_URL = "jdbc:postgresql://localhost:5433/postgres";
	    final String DB_USER = "postgres";
	    final String DB_PASSWORD = "password";

	    public Connection getConnection() throws SQLException, ClassNotFoundException {
	        // JDBCドライバを明示的にロード
	        Class.forName("org.postgresql.Driver");
	        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	    }
	

    public UserDTO selectByLoginId(String loginId) throws ClassNotFoundException, SQLException {
    	UserDTO dto = null;
        
    	//
    	Connection conn = getConnection();
    	
    	try {
    		//
    		PreparedStatement ps = conn.prepareStatement("SELECT * FROM users7 WHERE login_id = ?");
    		ps.setString(1, loginId);
    		ResultSet rs = ps.executeQuery();
    		
    		//
    		if(rs.next()) {
				    dto = new UserDTO();
				    dto.setId(rs.getInt("id"));
				    dto.setLoginId(rs.getString("login_id"));
				    dto.setId_check(rs.getBoolean("id_check"));
				    dto.setPassword(rs.getString("password"));
				    dto.setName(rs.getString("name"));
				    dto.setCreatedAt(rs.getTimestamp("created_at"));
				    dto.setNew_list(rs.getBoolean("new_list"));
				    dto.setRental_books(rs.getInt("rental_books"));
			}
    			
    		
    	}catch(SQLException e) {  //例外が発生した場合はこちらからコンソール上で履歴を表示する
    		e.printStackTrace();
    	}
    	return dto; //上記で得たdto の値を返り値として返す 
    }
    //ここから追加分
    public int insert(UserDTO dto) throws ClassNotFoundException, SQLException {
    	int result = 0;
    	//データベースへ接続
    	Connection conn = getConnection();
    	//トランザクション処理を開始
    	TransactionManager tm = new TransactionManager(conn);
    	
    	try {
    		PreparedStatement ps = conn.prepareStatement("INSERT INTO users7(login_id, id_check, password, name, new_list, rental_books) VALUES(?,?,?,?,?,?)");
    		ps.setString(1, dto.getLoginId());
    		ps.setBoolean(2, false);
    		ps.setString(3, dto.getPassword());
    		ps.setString(4, dto.getName());
    		ps.setBoolean(5, false);
    		ps.setInt(6, dto.getRental_books());
    		
   		
    		//DBへのinsertが成功した件数がint型で返却される
    		result = ps.executeUpdate();
    		tm.commit();
    	}catch(SQLException e) {
    		tm.rollback();
    		e.printStackTrace();
    	}
    	tm.close();
    	return result;
    }

    	
    	//ここから追加分　（0730～）
    	public int edit(UserDTO dto) throws ClassNotFoundException, SQLException {
			int result = 0;
			
			//データベースへ接続
			Connection conn = getConnection();
			//トランザクション処理を開始
			TransactionManager tm = new TransactionManager(conn);
			//データベースへ接続
			try {
				PreparedStatement ps = conn.prepareStatement("UPDATE users7 SET password = ?, name = ? WHERE login_id = ?");
				ps.setString(1, dto.getPassword());
				ps.setString(2, dto.getName());
				ps.setString(3, dto.getLoginId());
				
				//DBへのupdateが成功した件数がint型で返却される
				result = ps.executeUpdate();
				tm.commit();
			}catch(SQLException e) {
				tm.rollback();
				e.printStackTrace();
			}
			tm.close();
			return result;	
    }
    	
    	public int delete(UserDTO dto) throws ClassNotFoundException, SQLException {
    		int result = 0;
    		
    		//データベースへ接続
    		Connection conn = getConnection();
    		//トランザクション処理を開始
    		TransactionManager tm = new TransactionManager(conn);
    		
    		// データベースへ接続
    		try {
    			PreparedStatement ps = conn.prepareStatement("DELETE FROM users7 WHERE login_id = ?");
    			ps.setString(1, dto.getLoginId());
    			
    			// DBへのdeleteが成功した件数がint型で返却される
    			result = ps.executeUpdate();
    			tm.commit();
    		}catch(SQLException e) {
    			tm.rollback();
    			e.printStackTrace();
    		}
    		tm.close();
    		return result;
    	}
    	
//    	// 貸出冊数を加算
//    	public void incrementRentalBooks(int userId, int count) throws SQLException {
//    	    String sql = "UPDATE users SET rental_books = rental_books + ? WHERE id = ?";
//    	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//    	        pstmt.setInt(1, count);
//    	        pstmt.setInt(2, userId);
//    	        pstmt.executeUpdate();
//    	    }
   // 	}

}