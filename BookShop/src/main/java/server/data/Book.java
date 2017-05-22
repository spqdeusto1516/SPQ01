package server.data;


import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.*;


@PersistenceCapable(detachable="true")
public class Book implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private int ISBN;
	private String title;
	private String category;
	private String edition;
	private String author;
	private double price;
	private String description;
	private String img;
	
	public Book(int ISBN, String title, String category, String edition, String author, double price, String description, String img){
		this.ISBN = ISBN;
		this.title = title;
		this.category = category;
		this.edition = edition;
		this.author = author;
		this.price = price;
		this.description = description;
		this.img = img;
	}
	
	public Book(int ISBN, String title, String author, double price){
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	@Persistent(defaultFetchGroup="true", mappedBy="book", dependentElement="true")
	@Join	
	List<Review> reviews = new ArrayList<Review>();
	
	@Persistent(defaultFetchGroup="true", dependentElement="true")
	@Join	
	List<User> users = new ArrayList<User>();
	
	public void addReview(Review review){
		reviews.add(review);
	}
	public void removeReview(Review review){
		reviews.remove(review);
	}
	public List<Review> getReviews(){
		return this.reviews;
	}
	
	public void addUser(User user){
		users.add(user);
	}
	public void removeUser(User user){
		users.remove(user);
	}
	public List<User> getUsers(){
		return this.users;
	}

	public int getISBN() {
		return ISBN;
	}
	
	public void setISBN(int ISBN) {
		this.ISBN = ISBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getEdition() {
		return edition;
	}
	
	public void setEdition(String edition) {
		this.edition = edition;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public double  getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
		
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	public String toString() {
		 return "Book: ISBN --> " + this.ISBN + ", title -->  " + this.title + ",  author -->  " + this.author + ", price --> "+ this.price;
	}
}