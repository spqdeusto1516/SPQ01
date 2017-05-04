package server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import server.data.*;



public interface IRemote extends Remote{

	 boolean registerUser(String email, String password,boolean role) throws RemoteException;
	 boolean addBook(Book book) throws RemoteException;

	List<Book> showBooksInStore() throws RemoteException;
	//List<Book> showOwnedBooks(String email) throws RemoteException;
	
	//List<Book> showReviewsOfBook(int ISBN) throws RemoteException;
	//List<Book> showReviewsOfBook(String title) throws RemoteException;
	List<User> getAllUsers() throws RemoteException;
	List<Review> getAllReviews() throws RemoteException;
	Book bookTest()throws RemoteException;
	Book getBookByISBN	(int ISBN)throws RemoteException;
	Book getBookByTitle	(String title)throws RemoteException;
	
	Review getReview	(int id_review)throws RemoteException;
	User	getUser	(String email)throws RemoteException;
	
	 boolean buyBook(String email, String book_title) throws RemoteException;
	 boolean addReview(Book b, Review r, User u) throws RemoteException;
	
}
