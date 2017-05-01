package client;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import server.data.*;
import server.remote.*;


public class Client {
	
	private static String[] mainMenu = {"Show Books on store", "Show owned books", "Buy book"};
	final static  Logger logger = LoggerFactory.getLogger(Client.class);
	
	public static void displayMenu(String[] options){
		logger.info("Insert the option number to select an action. If you want to go back, input 'b'; if you want to exit the application, input 'quit'");
		for(int i = 0; i<options.length; i++){
			logger.info((i+1) + ".- " + options[i]);
		}
	}
	
	public static void showBooks(IRemote server){
		List<Book> books = null;
		List<User> users = null;
		List<Review> reviews = null;
	try {
			
			books = server.showBooksInStore();
			users = server.getAllUsers();
			reviews = server.getAllReviews();
			
			
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
		
		for(int i = 0; i<books.size(); i++){
			Book b = books.get(i);
			logger.info((i+1) + ".-" + b.toString());
		}
		for(int i = 0; i<users.size(); i++){
			User u = users.get(i);
			logger.info((i+1) + ".-" + u.toString());
		}
		for(int i = 0; i<reviews.size(); i++){
			Review r = reviews.get(i);
			logger.info((i+1) + ".-" + r.toString());
	//		logger.info("EMAIL USER IN REVIEW" + r.getUser().getEmail());
	//		logger.info("BOOK TITTLE IN REVIEW"+ r.getBook().getTitle());
			
		}
		
	}

	public static void main(String[] args) {
		
		if (args.length != 3) {
			logger.info("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			IRemote server = (IRemote) java.rmi.Naming.lookup(name);
			
			server.registerUser("dipina", "dipina",false);
		
			
		
	
			
			showBooks(server);
	
			/**	
			showGames(server, null);
		try {
			String username = "aihnoa";
			
			String input = "";
				
			do{
				displayMenu(mainMenu);
				input = System.console().readLine();
				switch(input){
				case("1"):
					//Show games
					showGames(server, null);
					break;
				case("2"):
					showGames(server, "aihnoa");
					break;
				case("3"):
					//Buy game
					logger.info("Insert a games Id to select it; If you want to go back, input 'b'; if you want to exit the application, input 'quit'");
					showGames(server, null);
					input = System.console().readLine();
					
					String n = "HL1";
					
					if(server.buyGame(username, n)){
						logger.info("Game bought successfully");
					}
					break;
				case("b"):
					break;
				default:
					logger.info("Invalid input");
					break;
				}
				
			} while(!(input.equals("exit")));
			
			*/
			
			
		} catch (Exception e) {
			logger.error("RMI Example exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}