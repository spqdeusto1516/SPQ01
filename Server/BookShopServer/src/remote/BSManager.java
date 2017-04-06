package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import Dao.DaoFactory;
import Server.AGateway;
import Server.AirGateway;
import Server.PGateway;
import bookShopSystem.Book;
//import bookShopSystem.Passenger;
//import bookShopSystem.Reservation;
import bookShopSystem.User;
import dto.BookDTO;
import dto.BookDtoAssembler;

public class BSManager extends UnicastRemoteObject implements IBookShopManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Book[] ff;
	User user;
	DaoFactory DaoF;
	PGateway Pgateway;
	AGateway Agateway;
	AirGateway Airgateway;
	
	public BSManager() throws RemoteException {
		super();
		DaoF =new DaoFactory();
		Agateway = new AGateway();
		Pgateway= new PGateway();
		Airgateway= new AirGateway();
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean payV(int num, int code) {
		// TODO Auto-generated method stub
		System.out.println("sending payment 1");
		return Pgateway.payV(num, code);
	}

	@Override
	public boolean payP(int num, int code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkV(int num, int code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkP(int num, int code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkAvF(String name) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean checkAvG(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BookDTO> getBooks() {
		// TODO Auto-generated method stub
		ff =Airgateway.getFlights();
		System.out.println(ff[3].getAirlineCode()+" "+ff[3].getDeparture());
		
		FlightDtoAssembler a = new FlightDtoAssembler();
		
		return a.assemble(ff);
	}

	@Override
	public boolean checkUserF(String name,String pass) {
		// TODO Auto-generated method stub
		boolean a= Agateway.checkUserF(pass,name);
		user =new User("log", pass, name);
		//DaoF.creatDao("User").storeObject(user);
		return a;
	}

	@Override
	public boolean checkUserG(String name,String pass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int makeRReservation(String acronim, String[] passengers) throws RemoteException {
		// TODO Auto-generated method stub
		int  i=6;
		for (i = 0; i < ff.length; i++) {
			System.out.println(acronim+" "+ff[i].getAirlineCode());
			if(ff[i].getAirlineCode()==Integer.parseInt(acronim)){
				break;
			}
		}
		
		System.out.println("creating reservation");
		
		Reservation ress=new Reservation(1, 12, new Date(1100), 1, 100f);
		
		ress.setFlight(ff[i]);
		
		ress.setUser(user);
	
		Passenger p = new Passenger(2,passengers[1]);
		
		ress.setPassenger(p);
	
		ress.setPassenger(new Passenger(1,passengers[0]));
		DaoF.creatDao("Reservation").storeObject(ress);
		
		return ress.getReservationCode();
	}

}
