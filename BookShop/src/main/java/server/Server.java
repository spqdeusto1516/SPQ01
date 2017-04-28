package server;

import java.rmi.Naming;

import db.DB;
import db.IDB;
import server.data.*;
import server.remote.*;


public class Server{

public static void addStuff(){

	//	DATOS DE PRUEBAS
		Book b =new Book(1,"HL1","pabloAut",0.2);
		Book b1 =new Book(2,"HL2","maria",0.2);
		Book b2 =new Book(3,"Skyrim","ainhoa",0.2);
		Book b3= new Book(4,"Oblivion","joel",0.2);

		User a =  new User ("aihnoa","qwerty",false);

		IDB db = new DB();

		db.addBookToDb( b);

		db.addBookToDb( b1);
		db.addBookToDb( b2);
		db.addBookToDb( b3);



		db.registerUser("pablo", "qwerty", false);


	//	db.buyGame(a.getLogin(), g.getName());
	//	db.buyGame(a.getLogin(), g2.getName());




}
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

			//DB testing
			addStuff();

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
