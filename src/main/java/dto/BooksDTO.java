package dto;

import java.sql.Timestamp;

public class BooksDTO {
	
	private int id;
	private String title;
	private String author;
	private String publisher;
	private String genre;
	private String book_area;
	private Timestamp createdAt;
	private Boolean rental_status;
	private String rental_date;
	

	//引数無しコンストラクタ
	public BooksDTO() {}
	
	//引数有コンストラクタ
	public BooksDTO(int id, String title, String author, String publisher, String genre, String book_area, Boolean rental_status, String rental_date) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.book_area = book_area;
		this.rental_status = rental_status;
		this.rental_date = rental_date;
		
	}	
	
	//getter,setter CreatedeAtnosetterはのぞく ・・・id_check 追加0820
	public int getBook_id() {
			return id;
	}
	public void setBook_id(int book_id) {
		this.id = id;
		
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getBook_area() {
		return book_area;
	}

	public void setBook_area(String book_area) {
		this.book_area = book_area;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public Boolean getRental_status() {
		return rental_status;
	}

	public void setRental_status(Boolean rental_status) {
		this.rental_status = rental_status;

	}
	
	public String getRental_date() {
		return rental_date;
	}

	public void setRental_date(String rental_date) {
		this.rental_date = rental_date;

	}
}
