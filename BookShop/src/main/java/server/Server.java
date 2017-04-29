package server;

import java.rmi.Naming;

import db.DB;
import db.IDB;
import server.data.*;
import server.remote.*;


public class Server{


	public static void main(String[] args) {

		if (args.length != 3) {
			System.out.println("[S] How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IRemote objServer = new Remote();
			Naming.rebind(name, objServer);

	

			Review r1 = new Review( 1,  "Me come los huevos HL1",56.6);
			Review r2 = new Review( 2,  "Me come los huevos Mucho",28.6);
			Review r3 = new Review( 3,  "Me come los huevos Un mogolllon",100.6);
			Review r4 = new Review( 4,  "Carazo magic",100.6);
			
			Book b =new Book(1,"HL1","pabloAut",0.2);
			Book b1 =new Book(2,"HL2","maria",0.2);
			Book b2 =new Book(3,"Skyrim","ainhoa",0.2);
			Book b3= new Book(4,"Oblivion","joel",0.2);

			IDB db = new DB();

			db.addBookToDb(b);
			db.addBookToDb(b1);
			db.addBookToDb(b2);
			db.addBookToDb(b3);

			db.registerUser("jon", "qwerty", false);
			db.registerUser("pablo@mariaysusCommitsdeMofa.es", "qwerty", false);
			db.registerUser("Carazo@.es", "qwerty", false);
	
			User a1 =db.showUser("jon");						
			User a2 =db.showUser("pablo@mariaysusCommitsdeMofa.es");
			User a3 =db.showUser("Carazo@.es");
			
			db.addReviewToBook(b1, r1);	
			db.addReviewToBook(b3, r2);
			db.addReviewToBook(b3, r3);	
			
			Review r11 = db.showReview(r1.getId_review());
			Review r22 = db.showReview(r2.getId_review());
			Review r33 = db.showReview(r3.getId_review());
			
			db.addReviewToUser(a1, r11);							
			db.addReviewToUser(a2, r22);			
			db.addReviewToUser(a3, r33);
			
			// db.addReviewToBook(b3, r4);	
			// db.addReviewToUser(a4, r4);
			
		
			
			
		
			
		

			System.out.println("[S] Server '" + name + "' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			@SuppressWarnings("unused")
			String line  = stdin.readLine();






		} catch (Exception e) {
			System.err.println("[S] Server exception: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
