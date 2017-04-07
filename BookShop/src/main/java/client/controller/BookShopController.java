package client.controller;

import java.rmi.RemoteException;
import java.util.List;

import client.Client;

public class BookShopController {

	@SuppressWarnings("unused")
	private Client rsl;
	
	public BookShopController(String[] args) throws RemoteException {
		// Add your code HERE - Related to the Service Locator
		rsl=new Client();
		rsl.setService(args[0], args[1], args[2]);
		
		
		
	}
	
	public void hello(){
		rsl.getServiceB().hello();
	}
        
    public void exit(){
    	System.exit(0);
    }
    
    public static void main(String[] args) {
    	BookShopController bookshop = null;
		try {
			bookshop=new BookShopController(args);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookshop.hello();
	}
}
