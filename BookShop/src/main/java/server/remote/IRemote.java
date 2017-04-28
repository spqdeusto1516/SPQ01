package server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import server.data.*;

import java.rmi.RemoteException;

public interface IRemote extends Remote{
//	String sayMessage(String login, String password, String message) throws RemoteException;
	 boolean registerUser(String email, String password,boolean role) throws RemoteException;
	 //boolean addBook(Book book);SUPERUSER FUNCTION
	 //boolean addReview(String u, String b, Review r);
	List<Book> showBooksInStore() throws RemoteException;
	//List<Book> showOwnedBooks(String email) throws RemoteException;
	//boolean buyBook(String email, String title) throws RemoteException;
	//boolean buyBook(String email, String ISBN) throws RemoteException;
	
	//List<Book> showReviewsOfBook(int ISBN) throws RemoteException;
	//List<Book> showReviewsOfBook(String title) throws RemoteException;
	List<User> getAllUsers() throws RemoteException;
	//List<Review> getAllReviews() throws RemoteException;
	
	
}
