package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	
	private int id;
	private String loginId;
	private Boolean id_check;
	private String password;
	private String name;
	private Date createdAt;
	private String createdAtStr;
	private Boolean new_list;
	private int rental_books;
	
	//引数無しコンストラクタ
    public User() {}
    
  //引数ありコンストラクタ
    public User(String loginId, Boolean id_check, String password, String name, Boolean new_list, int rental_books){
    	this.loginId = loginId;
    	this.setId_check(id_check);
    	this.password = password;
    	this.name = name;
    	this.new_list = new_list;
    	this.setRental_books(rental_books);
    }
	
    //getter, setter createdAtStr のsetterはのぞく

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		this.createdAtStr = new SimpleDateFormat("yyyy年MM月dd日 hh時mm分").format(createdAt);
	}

	public String getCreatedAtStr() {
		return createdAtStr;
	}

	public Boolean getNew_list() {
		return new_list;
	}

	public Boolean getId_check() {
		return id_check;
	}

	public void setId_check(Boolean id_check) {
		this.id_check = id_check;
	}

	public int getRental_books() {
		return rental_books;
	}

	public void setRental_books(int rental_books) {
		this.rental_books = rental_books;
	}
	
//	public void setNew_list(Boolean New_list) {
//		this.new_list = new_list;
//	}
}