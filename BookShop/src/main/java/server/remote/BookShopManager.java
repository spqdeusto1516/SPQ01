package server.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BookShopManager extends UnicastRemoteObject implements IBookShopManager {

	public BookShopManager() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public int hello() {
		// TODO Auto-generated method stub
		return 2;
	}
	
}
