package server.remote;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import server.data.*;
import db.*;


public class Remote extends UnicastRemoteObject implements IRemote {

	private static final long serialVersionUID = 1L;
	final static  Logger logger = LoggerFactory.getLogger(Remote.class);

	public Remote() throws RemoteException {
		super();

	}

	@Override
	public boolean registerUser(String email, String password, boolean role) throws RemoteException {
		// TODO Auto-generated method stub
		IDB db = new DB();
		//change to objetc the parameters
		return	db.registerUser(email, password, role);
	}

	@Override
	public List<Book> showBooksInStore() throws RemoteException {
		// TODO Auto-generated method stub

		IDB db = new DB();
		List<Book> books = db.getAllBooks();
		if(books.isEmpty()){
			throw new RemoteException("No books on store");
		}
		else{
			return(books);
		}
	}

	@Override
	public List<User> getAllUsers() throws RemoteException {
		// TODO Auto-generated method stub

		IDB db = new DB();
		List<User> users = db.getAllUsers();
		if(users.isEmpty()){
			throw new RemoteException("No users");
		}
		else{
			return(users);
		}
	}



	@Override
	public boolean addBook(Book book) throws RemoteException{
		if(book!=null){
			IDB db = new DB();
			return db.addBookToDb(book);
		}else{
			throw new RemoteException("Invalid book");
		}
	}

	@Override
	public List<Review> getAllReviews() throws RemoteException {

		IDB db = new DB();
		List<Review> reviews = db.getAllReviews();
		if(reviews.isEmpty()){
			throw new RemoteException("No Reviews");
		}
		else{
			return(reviews);
		}
	}

	@Override
	public Book bookTest() {

		Book b = new Book(1,"Prueba de librooo","Maria", 19.90);

		IDB db = new DB();

		db.addBookToDb(b);
		Book b1=db.showBookByTitle(b.getTitle());
		logger.info("Este es el libro"+ b1);

		return(b1);
	}

	@Override
	public Book getBookByISBN(int ISBN) throws RemoteException{
		// TODO Auto-generated method stub
		IDB db =new DB();
		Book b = null;
		try{
			b = db.showBookByISBN(ISBN);
		}catch (Exception e){

		}
		return b;
	}

	@Override
	public Book getBookByTitle(String title) throws RemoteException{
		// TODO Auto-generated method stub
		IDB db =new DB();
		Book b = null;
		try{
			b=db.showBookByTitle(title);
		}catch (Exception e){

		}
		return b;
	}

	@Override
	public Review getReview(int id_review) throws RemoteException {
		// TODO Auto-generated method stub
		IDB db =new DB();
		Review r=null;
		try{
			r= db.showReview(id_review);
		}catch (Exception e){

		}
		return r;

	}

	@Override
	public User getUser(String email) throws RemoteException {
		IDB db =new DB();
		if(email != null){

			return db.showUser(email);


		}else{
			throw new RemoteException();



		}


	}

	@Override
	public boolean buyBook(String email, String book_title) throws RemoteException{
		// TODO Auto-generated method stub
		IDB db =new DB();
		boolean a=false;
		try{
			a = db.buyBook(email, book_title);
		}catch(Exception e){

		}
		return a;
	}

	public boolean addReview(Book b, Review r, User u) {
		// TODO Auto-generated method stub
		IDB db =new DB();
		boolean a=false;
		try{
			a = db.addReview(b, r, u);
		}catch(Exception e){

		}
		return a;
	}
	public List<Review> getUserReviews(String email) {
		IDB db =new DB();
		List<Review> userReviews=null;
		try{
			userReviews= db.getUserReviews(email);
		}catch(Exception e){

		}
		return userReviews;
	}

	public List<Review> getBookReviews(String title) {
		IDB db =new DB();
		List<Review> bookReviews=null;
		try{
			bookReviews= db.getBookReviews(title);
		}catch(Exception e){

		}
		return bookReviews;
	}
	public  double averageRatingByBook(String title){
		IDB db =new DB();
		double average=0;
		try{
			average= db.averageRatingByBook(title);
		}catch(Exception e){

		}
		return average;

	}
	public  double averageRatingByUser(String email){
		IDB db =new DB();
		double average=0;
		try{
			average= db.averageRatingByUser(email);
		}catch(Exception e){

		}
		return average;

	}

	
	@Override
	public void deleteReview(int id_review){
		// TODO Auto-generated method stub
		IDB db = new DB();
		try{
			db.deleteReview(id_review);


		}catch(Exception e){

		}  
	}
	@Override
	public void deleteBook(int ISBN){
		// TODO Auto-generated method stub
		IDB db = new DB();
		try{
			db.deleteBook(ISBN);


		}catch(Exception e){

		}  
	}
/**
 * @return boolean true if the book is created and stored in db
 */
	@Override
	public boolean addBook(int ISBN, String title, String category, String edition, String author, double price,
			String description, String img) {
		// TODO Auto-generated method stub
		IDB db = new DB();
		Book b=new Book(ISBN,  title,  category,  edition,  author,  price,
				 description,  img);
		try{
			db.addBookToDb(b);
		}catch(Exception e){

		}  
		
		
		return false;
	}

}