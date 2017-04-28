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

import server.data.*;
import db.*;


public class Remote extends UnicastRemoteObject implements IRemote {

	private static final long serialVersionUID = 1L;
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
	
}