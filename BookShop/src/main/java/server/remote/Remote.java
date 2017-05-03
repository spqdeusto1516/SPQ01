package server.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import server.data.*;
import db.*;


public class Remote extends UnicastRemoteObject implements IRemote {

	private static final long serialVersionUID = 1L;
	final static  Logger logger = LoggerFactory.getLogger(Remote.class);
	private int cont = 0;
	private PersistenceManager pm=null;
	private Transaction tx=null;

	public Remote() throws RemoteException {
		super();
//		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
//		this.pm = pmf.getPersistenceManager();
//		this.tx = pm.currentTransaction();
	//}
	
	//protected void finalize () throws Throwable {
	//	if (tx.isActive()) {
  //          tx.rollback();
 //       }
 //       pm.close();
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
	public Book getBookByISBN(int ISBN) {
		// TODO Auto-generated method stub
		IDB db =new DB();
		return db.showBookByISBN(ISBN);
	}

	@Override
	public Book getBookByTitle(String title) {
		// TODO Auto-generated method stub
		IDB db =new DB();
		return db.showBookByTitle(title);
	}

	@Override
	public Review getReview(int id_review) {
		// TODO Auto-generated method stub
		IDB db =new DB();
		return db.showReview(id_review);
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		IDB db =new DB();
		return db.showUser(email);
	}
	
	@Override
	public boolean buyBook(String email, String book_title) {
		// TODO Auto-generated method stub
		IDB db =new DB();
		return db.buyBook(email, book_title);
	}
	

	
	
	
}