package server.data;

//trial
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import javax.jdo.annotations.*;


@PersistenceCapable(detachable="true")
public class Book {
	
	@PrimaryKey
	private int ISBN;
	private String title;
	private String category;
	private String edition;
	private String author;
	private float price;
	private String description;
	private String img;
	
	public Book(int ISBN, String title, String category, String edition, String author, float price, String description, String img){
		this.ISBN = ISBN;
		this.title = title;
		this.category = category;
		this.edition = edition;
		this.author = author;
		this.price = price;
		this.description = description;
		this.img = img;
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
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
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
}