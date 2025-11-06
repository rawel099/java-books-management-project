package dto;

import java.time.LocalDate;

public class BookDTO {
	private int id;
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private String bookArea;
    private boolean rentalStatus;
	private String rentalDate;
	private String userId;
	
	
	private LocalDate returnDueDate;  // 返却予定日

	public LocalDate getReturnDueDate() {
	    return returnDueDate;
	}
	public void setReturnDueDate(LocalDate returnDueDate) {
	    this.returnDueDate = returnDueDate;
	}
	

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRentalStatus(boolean rentalStatus) {
		this.rentalStatus = rentalStatus;
	}
	// getter / setter
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
	public boolean isRentalStatus() {
		return rentalStatus;
	}
	public String getRentalDate() {return rentalDate;}
	public void setRentalDate(String date) {this.rentalDate = date;	}
	public void setRentalDate(LocalDate localDate) {
	}
	public String getUserId() { return userId; }
	public void setUserId(String string) {this.userId = string; }
	
}