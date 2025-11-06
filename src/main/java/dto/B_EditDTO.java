package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class B_EditDTO implements Serializable {
    private int Id;
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private String bookArea;
    private boolean rentalStatus; // true = 貸出可, false = 貸出中
    private LocalDateTime createdAt;

    //public B_EditDTO() {}

    // コンストラクタ
    public B_EditDTO(int Id, String title, String author, String publisher, 
                     String genre, String bookArea, boolean rentalStatus, LocalDateTime createdAt) {
        this.Id = Id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.bookArea = bookArea;
        this.rentalStatus = rentalStatus;
        this.createdAt = createdAt;
    }

    public B_EditDTO(int id2, String title2, String author2, String publisher2, String genre2, String bookArea2) {
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
}