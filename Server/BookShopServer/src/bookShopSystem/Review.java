package bookShopSystem;

import java.awt.print.Book;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Review {

	@PrimaryKey
	private int id_review;
	private User user;
	private Book book;
	private String comment;
	private float rating;
	
	public Review(int id_review, User user, Book book, String comment, float rating) {
		super();
		this.id_review = id_review;
		this.user = user;
		this.book = book;
		this.comment = comment;
		this.rating = rating;
	}
	
	public int getId_review() {
		return id_review;
	}
	public void setId_review(int id_review) {
		this.id_review = id_review;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	} 

}
