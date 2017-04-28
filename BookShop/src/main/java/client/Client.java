package client;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import server.data.*;
import server.remote.*;


public class Client {
	
	private static String[] mainMenu = {"Show games on store", "Show owned games", "Buy game"};
	
	public static void displayMenu(String[] options){
		System.out.println("Insert the option number to select an action. If you want to go back, input 'b'; if you want to exit the application, input 'quit'");
		for(int i = 0; i<options.length; i++){
			System.out.println((i+1) + ".- " + options[i]);
		}
	}
	
	public static void showGames(IRemote server){
		List<Book> books = null;
		List<User> users = null;
		List<Review> reviews = null;
	try {
			
			books = server.showBooksInStore();
			users = server.getAllUsers();
			reviews = server.getAllReviews();
			
			
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
		
		for(int i = 0; i<books.size(); i++){
			Book b = books.get(i);
			System.out.println((i+1) + ".-" + b.toString());
		}
		for(int i = 0; i<users.size(); i++){
			User u = users.get(i);
			System.out.println((i+1) + ".-" + u.toString());
		}
		for(int i = 0; i<reviews.size(); i++){
			Review r = reviews.get(i);
			System.out.println((i+1) + ".-" + r.toString());
			System.out.println("EMAIL USER IN REVIEW" + r.getUser().getEmail());
			System.out.println("BOOK TITTLE IN REVIEW"+ r.getBook().getTitle());
			
		}
		
	}

	public static void main(String[] args) {
		
		if (args.length != 3) {
			System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			IRemote server = (IRemote) java.rmi.Naming.lookup(name);
			
			server.registerUser("dipina", "dipina",false);
		
			
		
	
			
			showGames(server);
	
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
					System.out.println("Insert a games Id to select it; If you want to go back, input 'b'; if you want to exit the application, input 'quit'");
					showGames(server, null);
					input = System.console().readLine();
					
					String n = "HL1";
					
					if(server.buyGame(username, n)){
						System.out.println("Game bought successfully");
					}
					break;
				case("b"):
					break;
				default:
					System.out.println("Invalid input");
					break;
				}
				
			} while(!(input.equals("exit")));
			
			*/
			
			
		} catch (Exception e) {
			System.err.println("RMI Example exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}