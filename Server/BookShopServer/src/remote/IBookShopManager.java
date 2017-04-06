package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.BookDTO;

public interface IBookShopManager extends Remote {
	public int makeRReservation(String acronim, String[] passengers) throws RemoteException;
	public boolean payV(int num, int code) throws RemoteException;
	public boolean payP(int num, int code) throws RemoteException;
	public boolean checkV(int num, int code) throws RemoteException;
	public boolean checkP(int num, int code) throws RemoteException;
	public boolean checkAvF(String name) throws RemoteException;
	public boolean checkAvG(String name) throws RemoteException;
	public List<BookDTO> getFlights() throws RemoteException;
	public boolean checkUserF(String name,String pass) throws RemoteException;
	public boolean checkUserG(String name,String pass) throws RemoteException;
}
