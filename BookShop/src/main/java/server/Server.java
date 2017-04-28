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
			
			Book b =new Book(1,"HL1","pabloAut",0.2);
			Book b1 =new Book(2,"HL2","maria",0.2);
			Book b2 =new Book(3,"Skyrim","ainhoa",0.2);
			Book b3= new Book(4,"Oblivion","joel",0.2);

			IDB db = new DB();

			db.addBookToDb(b);
			db.addBookToDb(b1);
			db.addBookToDb(b2);
			db.addBookToDb(b3);

			db.registerUser("aihnoa@mecomehuevos.es","qwerty",false);
			db.registerUser("pablo@mariaysusCommitsdeMofa.es", "qwerty", false);
	
			User a =db.showUser("aihnoa@mecomehuevos.es");
						
			User a1 =db.showUser("pablo@mariaysusCommitsdeMofa.es");
			
			db.createReview(a.getEmail(), "HL1", r1);
			//db.createReview(a1.getEmail(), "HL1", r2);
			db.createReview(a1.getEmail(), "HL2", r3);
			
			
		
			
		

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
