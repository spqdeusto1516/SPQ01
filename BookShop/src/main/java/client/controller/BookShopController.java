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
		System.out.println(args[0]);
		rsl.setService(args[0], args[1], args[2]);
		
		
		
	}
	
	public int hello(){
		try {
			return rsl.getServiceB().hello();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
        
    public void exit(){
    	System.exit(0);
    }
}
