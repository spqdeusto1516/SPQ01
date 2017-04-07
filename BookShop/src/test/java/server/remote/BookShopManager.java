package server.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BookShopManager extends UnicastRemoteObject implements IBookShopManager {

	protected BookShopManager() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public int hello() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			
			IBookShopManager adminService = new BookShopManager();			
			Naming.rebind(name, adminService);
			System.out.println("* TVProgram Admin Service '" + name + "' active and waiting...");
			
		} catch (Exception e) {
			System.err.println("$ TVProgramManager exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
