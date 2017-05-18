package db;


	import java.rmi.RemoteException;
import java.util.List;

	import server.data.*;

	public interface IDB {
		
		/**
		 * get all the Users of the Database
		 * @return A List<User> of all users 
		 */
		List<User> getAllUsers();
		
		/**
		 * get all the Reviews of the Database
		 * @return A List<Review> of all users 
		 */
		List<Review> getAllReviews();
		
		/**
		 * get all the Books of the Database
		 * @return A List<Book> of all users 
		 */
		List<Book> getAllBooks();
		
		  /**
		   * Allow the User to buy a selected book
		 * @param email Email of the buying user
		 * @param book_title the title of the book to buy
		 * @return true or false to tell if it worked
		 */
		boolean buyBook(String email, String book_title);
	
		/**
		  * creates a new user
		 * @param email 
		 * @param password
		 * @param role (true admin; false user)
		 * @return true or false to tell if it worked
		 */
		boolean registerUser(String email, String password,boolean role);

		/**
		 * Adds a new book to the database
		 * @param b the book to add
		 * @return true or false to tell if it worked
		 */
		boolean addBookToDb(Book b);
		
		/**
		 * Adds a new Review to the database
		 * @param b the book its from
		 * @param r the review
		 * @param user the user that wrote it
		 * @return true or false to tell if it worked
		 */
		boolean addReview(Book b, Review r, User user );

		/**
		 * Get a book from  the database by its ISBN
		 * @param ISBN
		 * @return The Book 
		 */
		Book showBookByISBN	(int ISBN);
		
		/**
		 * Get a Book from the database by its title
		 * @param title of the book
		 * @return The book
		 */
		Book showBookByTitle	(String title);
		
		/**
		 * Get a Review from the database by its ID
		 * @param id_review
		 * @return The Review
		 */
		Review showReview	(int id_review);
		
		/**
		 * Get a User form the database by its email
		 * @param email Email of the user you want to get
		 * @return The User
		 */
		User showUser	(String email);
		
		/**
		 * Get all the reviews a User wrote 
		 * @param email of the user
		 * @return A List<Review> of a given user
		 */
		List<Review> getUserReviews(String email);
		
		/**
		 * Get all the reviews of a book
		 * @param title of the book
		 * @return A List<Review> of a given book
		 */
		List<Review> getBookReviews(String title);
		
		/**
		 * Gets the average rating of a book
		 * @param title of the book
		 * @return a double the average rating
		 */
		double averageRatingByBook(String title);
		
		/**
		 * Gets the average rating given by a user
		 * @param email of the user
		 * @return a double the average rating
		 */
		double averageRatingByUser(String email);
		
		/**
		 * Eliminates a Review from the database
		 * @param id_review 
		 * @return true or false to tell if it worked
		 */
		boolean deleteReview(int id_review);
		
		/**
		 * Eliminates a book from the database
		 * @param ISBN
		 * @return true or false to tell if it worked
		 */
		boolean deleteBook(int ISBN);
		

		
		
}
