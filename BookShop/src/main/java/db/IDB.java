package db;


	import java.util.List;

	import server.data.*;

	public interface IDB {
		
		  List<User> getAllUsers();
		  List<Review> getAllReviews();
		  List<Book> getAllBooks();
		
		  boolean buyBook(String email, String book_title);
	
			boolean registerUser(String email, String password,boolean role);
	
			boolean addBookToDb(Book b);
			boolean addReview(Book b, Review r, User user );
			
			Book showBookByISBN	(int ISBN);
			Book showBookByTitle	(String title);
			Review showReview	(int id_review);
			User showUser	(String email);
		
		
}
