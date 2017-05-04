package server;

import java.rmi.Naming;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DB;
import db.IDB;
import server.data.*;
import server.remote.*;


public class Server{

	final static  Logger logger = LoggerFactory.getLogger(Server.class);
	public static void main(String[] args) {

		
		
		if (args.length != 3) {
			logger.info("[S] How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IRemote objServer = new Remote();
			Naming.rebind(name, objServer);

	

			Review r1 = new Review( "Review 1",56.6);
			Review r2 = new Review( "Review 2",28.6);
			Review r3 = new Review( "Review 3",100.6);
			Review r4 = new Review( "Review 4",100.6);
			Review r5 = new Review( "Review 5",100.6);
			
			Book b4 =new Book(1,"Book1","pabloaut",0.2);
			Book b1 =new Book(2,"Book2","maria",0.2);
			Book b2 =new Book(3,"Book3","ainhoa",0.2);
			Book b3= new Book(4,"Book4","joel",0.2);

			IDB db = new DB();

			db.addBookToDb(b4);
			db.addBookToDb(b1);
			db.addBookToDb(b2);
			db.addBookToDb(b3);

			db.registerUser("jon", "qwerty", false);
			db.registerUser("pablo", "qwerty", false);
			db.registerUser("Carazo", "qwerty", false);
			db.registerUser("Alon", "qwerty", false);
	
			User a1 =db.showUser("jon");						
			User a2 =db.showUser("pablo");
			User a3 =db.showUser("Carazo");
			User a4 =db.showUser("Alon");
			
			
			db.addReview(b1, r1, a1);	
			db.addReview(b2, r2, a2);
			
			db.addReview(b3, r3, a2);
			db.addReview(b3, r4, a3);
			db.addReview(b2, r5, a4);
			
			
	
			db.buyBook("jon", "Book4");

		
			

		
			
			logger.info("[S] Server '" + name + "' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			@SuppressWarnings("unused")
			String line  = stdin.readLine();






		} catch (Exception e) {
			logger.error("[S] Server exception: ");
			logger.trace(e.getMessage());
			e.printStackTrace();
		}

	}
}
