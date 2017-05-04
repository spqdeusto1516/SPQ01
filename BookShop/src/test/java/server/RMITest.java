package server;


import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DAO;
import db.DB;
import db.IDAO;
import db.IDB;

import org.junit.AfterClass;

import server.data.*;
import server.remote.IRemote;
import server.remote.Remote;



import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;



/**
 * RMI Unit test for Simple Client / Server RMI Testing.
 * Testing the only the Remoteness
 */
//@Ignore

public class RMITest {
	// Properties are hard-coded because we want the test to be executed without external interaction
	
	private static String cwd = RMITest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;
	
	private IRemote remote;
	final static Logger logger = LoggerFactory.getLogger(RMITest.class);
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RMITest.class);
	}


	@BeforeClass static public void setUp() {
		// Launch the RMI registry
		class RMIRegistryRunnable implements Runnable {

			public void run() {
				try {
					java.rmi.registry.LocateRegistry.createRegistry(1099);
					logger.info("BeforeClass: RMI registry ready.");
				} catch (Exception e) {
					logger.error("Exception starting RMI registry:");
					logger.trace(e.getMessage());
					e.printStackTrace();
				}	
			}
		}
		
		rmiRegistryThread = new Thread(new RMIRegistryRunnable());
		rmiRegistryThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			logger.error("Interruption Exception");
			logger.trace(ie.getMessage());
			ie.printStackTrace();
		}
		
		class RMIServerRunnable implements Runnable {

			public void run() {
				logger.info("This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
				logger.info("**************: " + cwd);
				System.setProperty("java.rmi.server.codebase", "file:" + cwd);
				System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

				if (System.getSecurityManager() == null) {
					System.setSecurityManager(new SecurityManager());
				}

				String name = "//127.0.0.1:1099/MessengerRMIDAO";
				logger.info("BeforeClass - Setting the server ready TestServer name: " + name);

				try {
					
					IRemote remote = new Remote();
					Naming.rebind(name, remote);
				} catch (RemoteException re) {
					logger.error(" # Messenger RemoteException: ");
					logger.trace(re.getMessage());
					re.printStackTrace();
					System.exit(-1);
				} catch (MalformedURLException murle) {
					logger.error(" # Messenger MalformedURLException: ");
					logger.trace(murle.getMessage());
					murle.printStackTrace();
					System.exit(-1);
				}
			}
		}
		rmiServerThread = new Thread(new RMIServerRunnable());
		rmiServerThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			logger.error("Interruption Exception");
			logger.trace(ie.getMessage());
			ie.printStackTrace();
			
		}
	
	}
	

	@Before public void setUpClient() {
		try {
		System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//127.0.0.1:1099/MessengerRMIDAO";
		logger.info("BeforeTest - Setting the client ready for calling TestServer name: " + name);
		remote = (IRemote) java.rmi.Naming.lookup(name);
		}
		catch (Exception re) {
			logger.error(" # Messenger RemoteException: ");
			logger.trace(re.getMessage());
			re.printStackTrace();
			System.exit(-1);
		} 
		
	}
	
	@Test public void registerNewUserTest() {
		try{
			logger.info("Test 1 - Register new user");
			remote.registerUser("ipina", "ipina",false);
		}
		catch (Exception re) {
			logger.error(" RemoteException: " );
			logger.trace(re.getMessage());
			re.printStackTrace();
		} 
		/*
		 * Very simple test, inserting a valid new user
		 */
		assertTrue( true );
	}
	
	
	
	
	@Test public void registerExistingUserTest() {
		try{
			logger.info("Test 2 - Register existing user. Change password");
			remote.registerUser("smith", "smith",false);
			// Silly way of testing the password testing
			remote.registerUser("smith", "doe",false);
			
		}
		catch (Exception re) {
			logger.error(" RemoteException: " + re.getMessage());
			logger.trace(re.getMessage());
			re.printStackTrace();
		} 
		/*
		 * Very simple test 
		 */
		assertTrue( true );
	}
	
	
		@Test public void bookTestValidation() {
		logger.info("Test 3 - Game Test ");
		
		
		Book b = new Book(1,"Prueba de librooo","Maria", 19.90);
	
		
		Book bookTest = null;
		
		try{	
			bookTest = remote.bookTest();		
			
		} catch (RemoteException e){
			logger.error(" # RemoteException: " + e.getMessage());
			logger.trace(e.getMessage());
			e.printStackTrace();
			
			
		}
	
		assertEquals(b.toString(), bookTest.toString());
		
	}
		
		@Test public void showBooksInStoreTest() {
			try{
				logger.info("Test 4 - showBooksInStore");
				Book b1 =new Book(2,"HL2","maria",0.2);
				remote.addBook(b1);
				remote.showBooksInStore();
				
			}
			catch (Exception re){
				logger.error(" RemoteException: " );
				logger.trace(re.getMessage());
				re.printStackTrace();
				
			}
			
			assertTrue( true );
			
			
			
		}
	
		@Test public void showUsersTest() {
			try{
				logger.info("Test 5 - showUsers");
				
				remote.registerUser("HL2","maria",false);
				remote.getAllUsers();
				
			}
			catch (Exception re){
				logger.error(" RemoteException: " );
				logger.trace(re.getMessage());
				re.printStackTrace();
				
			}
			
			assertTrue( true );
			
			
			
		}
		@Test public void getThings(){
			boolean a = true;
			
			try {
				logger.info("Test 6 - showUsers");
				
				User u1;
				Review r1 = new Review( "Review 1",56.6);
				Book b1 =new Book(2,"Book2","maria",0.2);
				Book b2=null;
				remote.registerUser("Maria","maria",false);
				u1=remote.getUser("Maria");
				
				remote.addBook(b1);
				
				
				
				b2 =remote.getBookByISBN(b1.getISBN());
				
				remote.addReview(b2, r1, u1);
				remote.getReview(r1.getId_review());
				remote.getAllReviews();
				remote.buyBook(u1.getEmail(), b2.getTitle());
				
				
			}
				catch (Exception re){
					logger.error(" RemoteException: " );
					logger.trace(re.getMessage());
					re.printStackTrace();
					a=false;
					
			}
			assertTrue(a);
		}
		@Test(expected=RemoteException.class)
		public void showUserFailTest() throws RemoteException{
				
				logger.info("Test 7 - showUsersFail");
				
				
				remote.getUser(null);
				
				
		
			
					
		}
		
	
/**
	@After public  void deleteDatabase() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
	
            logger.info("Deleting test users from persistence. Cleaning up.");
            Query<User> q1 = pm.newQuery(User.class);
            Query<Book> q2 = pm.newQuery(Book.class);
            Query<Review> q3 = pm.newQuery(Review.class);
            long numberInstancesDeleted1 = q1.deletePersistentAll();
            long numberInstancesDeleted2 = q2.deletePersistentAll();
            long numberInstancesDeleted3 = q3.deletePersistentAll();
           
			
            tx.commit();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	**/

	@AfterClass static public void tearDown() {
		try	{
			rmiServerThread.join();
			rmiRegistryThread.join();
		} catch (InterruptedException ie) {
			logger.error("Interruption Exception");
			logger.trace(ie.getMessage());
			ie.printStackTrace();
		}
		

	} 
}
