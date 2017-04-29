package db;


	import java.util.List;

	import server.data.*;

	public interface IDB {
		
		  List<User> getAllUsers();
		  List<Review> getAllReviews();
		  List<Book> getAllBooks();
		  
		  boolean buyBook(String u, String book_title);
		//  boolean createReview(String u, String book, Review r);
		
		
		boolean registerUser(String email, String password,boolean role);

		boolean addBookToDb(Book b);
		boolean addReviewToBook(Book b, Review r );
		boolean addReviewToUser(User u, Review r );
		
		Book showBookByISBN	(int ISBN);
		Book showBookByTitle	(String title);
		Review showReview	(int id_review);
		
		User	showUser	(String email);
		
		
}
