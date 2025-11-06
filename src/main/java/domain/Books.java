package domain;

//import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Books {
	
	private int bookId;
	private String title;
	private String author;
	private String publisher;
	private String genre;
	private String bookArea;
	private Date createdAt;
	private String createdAtStr;
	private Boolean rentalStatus;
	private String rentalDate;
	private String userId;
	
	//引数無しコンストラクタ
    public Books() {}
    
  //引数ありコンストラクタ
    public Books(int book_id, String title, String author, String publisher, String genre, String book_area, Boolean rental_status, String rental_date ,String user_id){
    	this.bookId = book_id;
    	this.title = title;
    	this.author = author;
    	this.publisher = publisher;
    	this.genre = genre;
    	this.bookArea = book_area;
    	this.rentalStatus = rental_status;
    	this.rentalDate = rental_date;
    	this.userId = user_id;
    }
	
    //getter, setter createdAtStr のsetterはのぞく

	public int getBookId() {
		return bookId;
	}

	public void setBook_id(int book_id) {
		this.bookId = book_id;
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
	
	public String getGgenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getBook_area() {
		return bookArea;
	}

	public void setBook_area(String book_area) {
		this.bookArea = book_area;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		this.createdAtStr = new SimpleDateFormat("yyyy年MM月dd日").format(createdAt);
	}

	public String getCreatedAtStr() {
		return createdAtStr;
	}

	public Boolean getRental_status() {
		return rentalStatus;
	}
	
	public void setRental_status(Boolean rental_status) {
		this.rentalStatus = rental_status;
	}
	
	public String getRental_date() {
		return rentalDate;
	}

	public void setRental_date(String rental_date) {
		this.rentalDate = rental_date;
	}
	public void setUserId(String userId) {
		this.userId = userId; 
		}
	public String getUserId() { 
		return userId;
	}
	
}