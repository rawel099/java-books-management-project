package dto;

import java.sql.Timestamp;

public class UserDTO {
	
	private int id;
	private String loginId;
	private Boolean id_check;
	private String password;
	private String name;
	private Timestamp createdAt;
	private Boolean new_list;
	private int rental_books;
	

	//引数無しコンストラクタ
	public UserDTO() {}
	
	//引数有コンストラクタ
	public UserDTO(String loginId, Boolean id_check,String password, String name, Boolean new_list, int rental_books) {
		this.loginId = loginId;
		this.id_check = id_check;
		this.password = password;
		this.name = name;
		this.new_list = new_list;
		this.rental_books = rental_books;
	}	
	
	//getter,setter CreatedeAtnosetterはのぞく ・・・id_check 追加0820
	public int getId() {
			return id;
	}
	public void setId(int id) {
		this.id = id;
		
	}
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Boolean getId_check() {
		return id_check;
	}

	public void setId_check(Boolean id_check) {
		this.id_check = id_check;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public Boolean getNew_list() {
		return new_list;
	}

	public void setNew_list(Boolean new_list) {
		this.new_list = new_list;
	}
	
	public int getRental_books() {
		return rental_books;
	}

	public void setRental_books(int rental_books) {
		this.rental_books = rental_books;
	}

	public boolean isIdCheck() {
		return false;
	}
}
