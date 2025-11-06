package dao;

import java.sql.Connection;
import java.sql.SQLException;



public class TransactionManager {
	private Connection conn;
	private boolean isCommit;
	
    
	//コンストラクタで、データベース接続情報を引数で受け取ってフィールドで保持する
	public TransactionManager(Connection conn) {
        this.conn = conn;
        try {
        	//自動コミットモードをオフに設定（トランザクションの開始）
        	conn.setAutoCommit(false);
        }catch(SQLException e) {
        	e.printStackTrace();
        }
	}
     //トランザクションをコミット（＝処理を確定させる）
	 //DB接続がない場合は例外をスローする。
	public void commit() {
		if(conn == null) {
				System.err.print("トランザクションが開始されていません");
		}else {
			isCommit = true;
		}
        
    }
	//トランザクションをロールバック（処理を巻き戻す）
	// DB　接続がない場合は例外をスローする。
	public void rollback() {
		if(conn == null) {
			System.err.print("トランザクションが開始されていません");
		}else {
			isCommit = false;
		}
	}
	//トランザクションを終了し、接続を閉じる。
	public void close() {
		try {
			if(conn != null) {
				if(isCommit) {
					conn.commit();//コミットが指示されていればコミット
				}else {
					conn.rollback();//そうでなければロールバック
				}
				conn.close();//接続を閉じる
				conn = null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
 }
