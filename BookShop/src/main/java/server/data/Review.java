package server.data;


import java.io.Serializable;

import javax.jdo.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@PersistenceCapable
public class Review implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	final static Logger logger = LoggerFactory.getLogger(Review.class);
	@PrimaryKey
	private int id_review; 
	
	@Persistent (defaultFetchGroup="true")
	private User user;
	
	@Persistent (defaultFetchGroup="true")
	private Book book;
	
	private String comment;
	private double rating;
	
	public Review( User user, Book book, String comment, double rating) {
		super();
		
		this.user = user;
		this.book = book;
		this.comment = comment;
		this.rating = rating;
	}
	 
	
	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Review( String comment, double rating) {
		super();
		
		this.comment = comment;
		this.rating = rating;
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
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}



	public int getId_review() {
		return id_review;
	}



	@Override
	public String toString() {
		return "Review [id_review=" + id_review +  ", comment=" + comment
				+ ", rating=" + rating + "]";
	}



	public void setId_review(int id_review) {
		this.id_review = id_review;
	} 
}
