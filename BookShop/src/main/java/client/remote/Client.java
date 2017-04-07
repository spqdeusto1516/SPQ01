package client.remote;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import remote.IBookingSystemManager;
import server.remote.IBookShopManager;

public class Client {

	/** 
	 * The Cache - Limitation: one server at a time
	 * Proposed improvement: list of services
	 */
	private IBookShopManager BookShopService;

    /** Creates a new instance of RMIServiceLocator */
    public Client(){ 
    
    }

    public void setService(String ip, String port, String serviceName){    
    	// Add your code to get the TARGET reference HERE    
    	String name = "//" + ip + ":" + port + "/" + serviceName;
		try {
			System.out.println(serviceName);
			BookShopService = (IBookShopManager) java.rmi.Naming.lookup(name);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
    }
    
    public IBookShopManager getServiceB() {    	
    	// Add your code to return the TARGET reference HERE
    	return BookShopService;
    }
}
