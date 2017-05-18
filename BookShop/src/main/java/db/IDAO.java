package db;

import server.data.*;
import java.util.List;

public interface IDAO {

	/**
	 * Store a user in the dataBase
	 * @param u User you want to store
	 * @return true of false to tell if it worked
	 */
	boolean storeUser(User u);
	
	/**
	 * Get a User form the database by its email
	 * @param email Email of the user you want to get
	 * @return The User
	 */
	User retrieveUser(String email);
	
	/**
	 * Updates the row in the database of a given User 
	 * @param u User you are updating
	 * @return true of false to tell if it worked
	 */
	boolean updateUser(User u);

	
	/**
	 * Get a Review from the database by its ID
	 * @param id_review
	 * @return The Review
	 */
	Review retrieveReview(int id_review );
	
	/**
	 * Updates the row in the database of a given Review
	 * @param r the review to update
	 * @return true of false to tell if it worked
	 */
	boolean updateReview(Review r);

	/**
	 * Store a Book in the database
	 * @param b the Book you want to store
	 * @return true of false to tell if it worked
	 */
	boolean storeBook(Book b);
	
	/**
	 * Get a Book from the database by its ISBN
	 * @param ISBN
	 * @return The Book
	 */
	Book retrieveBook(int ISBN );
	
	/**
	 * Updates the row in the database of a given Book
	 * @param b the book
	 * @return true of false to tell if it worked
	 */
	boolean updateBook(Book b);

	/**
	 * Get a Book from the database by its title
	 * @param title of the book
	 * @return The book
	 */
	Book retrieveBookByParameter(String title);
	
	// boolean deleteDatabase();

	//Get all entities

	/**
	 * get all the Users of the Database
	 * @return A List<User> of all users 
	 */
	public List<User> getAllUsers();
	
	/**
	 * get all the Reviews of the Database
	 * @return A List<Review> of all users 
	 */
	public List<Review> getAllReviews();
	
	/**
	 * get all the Books of the Database
	 * @return A List<Book> of all users 
	 */
	public List<Book> getAllBooks();
	
	/**
	 * Eliminates a Review from the database
	 * @param r the review to eliminate
	 */
	void deleteReview(Review r);
	/**
	 * Eliminates a book from the database
	 * @param b the book to eliminate
	 */
	void deleteBook(Book b);


}