package server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import server.data.*;



public interface IRemote extends Remote{

	 /**
	  * creates a new user
	 * @param email 
	 * @param password
	 * @param role (true admin; false user)
	 * @return true or false to tell if it worked
	 * @throws RemoteException
	 */
	boolean registerUser(String email, String password,boolean role) throws RemoteException;
	
	 /**
	  * adds a book to the database
	 * @param book
	 * @return true or false to tell if it worked 
	 * @throws RemoteException
	 */
	boolean addBook(Book book) throws RemoteException;
	

	/**
	 * gives you a all the books in the database
	 * @return a list<Book> with all the books in the database
	 * @throws RemoteException
	 */
	List<Book> showBooksInStore() throws RemoteException;
	//List<Book> showOwnedBooks(String email) throws RemoteException;
	
	//List<Book> showReviewsOfBook(int ISBN) throws RemoteException;
	//List<Book> showReviewsOfBook(String title) throws RemoteException;
	
	/**
	 * gives you a all the users in the database
	 * @return a list<User> with all the users in the database
	 * @throws RemoteException
	 */
	List<User> getAllUsers() throws RemoteException;
	
	/**
	 * gives you a all the Review in the database
	 * @return a list<Review> with all the books in the database
	 * @throws RemoteException
	 */
	List<Review> getAllReviews() throws RemoteException;
	
	/**
	 * Test 
	 * @return the test book
	 * @throws RemoteException
	 */
	Book bookTest()throws RemoteException;
	
	/**
	 * Get a book by its ISBN
	 * @param ISBN unique book ID
	 * @return a Book with the selected ISBN
	 * @throws RemoteException
	 */
	Book getBookByISBN	(int ISBN)throws RemoteException;
	
	/**
	 * Get a Book by its title
	 * @param title name of the book
	 * @return a Book with the selected Title
	 * @throws RemoteException
	 */
	Book getBookByTitle	(String title)throws RemoteException;
	
	
	/**
	 * Get a Review by its ID
	 * @param id_review unique number
	 * @return a Review
	 * @throws RemoteException
	 */
	Review getReview(int id_review)throws RemoteException;
	
	/**
	 * Get a User by its Email
	 * @param email
	 * @return a User
	 * @throws RemoteException
	 */
	User getUser(String email)throws RemoteException;
	
	
	 /**
	  * Buy a book
	 * @param email of the user
	 * @param book_title title of the book
	 * @return true of false to tell if it worked
	 * @throws RemoteException
	 */
	boolean buyBook(String email, String book_title) throws RemoteException;
	
	 /**
	  * add a new review to a book
	 * @param b the book to add a review
	 * @param r the Review
	 * @param u the User who made the review
	 * @return true of false to tell if it worked
	 * @throws RemoteException
	 */
	boolean addReview(Book b, Review r, User u) throws RemoteException;
	
	 /**
	  * Get all the Reviews a User Made
	 * @param email of the user
	 * @return a List<Review> 
	 * @throws RemoteException
	 */
	List<Review> getUserReviews(String email)  throws RemoteException;
	
	 /**
	  * Get all the Reviews of a Book
	 * @param title of the book
	 * @return List<Review>
	 * @throws RemoteException
	 */
	List<Review> getBookReviews(String title)  throws RemoteException;
	
	 /**
	  * Get the average Rating of a book
	 * @param title of the book
	 * @return a double 
	 * @throws RemoteException
	 */
	double averageRatingByBook(String title)  throws RemoteException;
	
	 /**
	  * Get the average Rating given by a User
	 * @param email
	 * @return a double 
	 * @throws RemoteException
	 */
	double averageRatingByUser(String email)  throws RemoteException;
	
	 /**
	  * Eliminates a Review
	 * @param id_review
	 * @throws RemoteException
	 */
	void deleteReview(int id_review) throws RemoteException;
	
	 /**
	  * Eliminates a Book
	 * @param ISBN
	 * @throws RemoteException
	 */
	void deleteBook(int ISBN) throws RemoteException;
	
	 /**
	  * Creates new book in the database
	 * @param ISBN unique identifier
	 * @param title  String of the book
	 * @param category genre of book
	 * @param edition 
	 * @param author
	 * @param price
	 * @param description
	 * @param img
	 * @return true of false to tell if it worked
	 * @throws RemoteException
	 */
	boolean addBook(int ISBN, String title, String category, String edition, String author, double price, String description, String img) throws RemoteException;
}
