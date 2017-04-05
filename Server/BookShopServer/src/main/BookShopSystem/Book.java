package BookShopSystem;
import java.util.List;
import java.util.ArrayList;


import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

//@PersistenceCapable
public class Book {

	//@PrimaryKey
	private int ISBN;
	private String title;
	private String category;
	private String edition;
	private String author;
	private float price;
	private int stock;
	private String description;
	

	
	
	
	
	
}