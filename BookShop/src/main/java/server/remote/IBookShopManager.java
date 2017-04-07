package server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBookShopManager extends Remote{
	public int hello() throws RemoteException;
}
