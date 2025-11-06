package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class R_BookDTO implements Serializable {
    private int Id;
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private String bookArea;
    private boolean rentalStatus; // true = 貸出可, false = 貸出中
    private LocalDateTime createdAt;
    private String user_id;

    public R_BookDTO() {}

    // コンストラクタ
    public R_BookDTO(int Id, String title, String author, String publisher, 
                     String genre, String bookArea, boolean rentalStatus, LocalDateTime createdAt, String user_id) {
        this.Id = Id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.bookArea = bookArea;
        this.rentalStatus = rentalStatus;
        this.createdAt = createdAt;
        this.user_id = user_id;
        
    }

    public R_BookDTO(int int1, String string, String string2, String string3, String string4, String string5,
			boolean boolean1, Object object) {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	// getter & setter
    public int getBookId() { return Id; }
    public void setBookId(int Id) { this.Id = Id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getBookArea() { return bookArea; }
    public void setBookArea(String bookArea) { this.bookArea = bookArea; }

    public boolean isRentalStatus() { return rentalStatus; }
    public void setRentalStatus(boolean rentalStatus) { this.rentalStatus = rentalStatus; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}