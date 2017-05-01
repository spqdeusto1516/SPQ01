package server;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;  

import java.rmi.RemoteException;

import junit.framework.JUnit4TestAdapter;

import org.junit.runner.RunWith;  
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;  
import org.mockito.runners.MockitoJUnitRunner; 

import org.junit.Before;
import org.junit.Test;
//import org.junit.Ignore;




import db.*;
import server.*;
import server.data.*;
import server.remote.*;



import org.junit.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 
 * @author cortazar
 * Testing of the Service Layer, mocking the DAO layer
 */
@RunWith(MockitoJUnitRunner.class)  
public class DAOMockTest {
	
	DB db;
	final static  Logger logger = LoggerFactory.getLogger(DAOMockTest.class);
	static int iteration = 0;
	
	@Mock
	IDAO dao;
		
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DAOMockTest.class);
	}

	
	@Before
	public void setUp() throws Exception {
		logger.info("Entering setUp: {}", iteration++);
		db = new DB(dao);
		logger.info("Leaving setUp");

	}

	@Test
	//@Ignore
	public void testRegisterUserCorrectly() {
		logger.info("Starting testRegisterUserCorrectly() ");
		// Stubbing - return a given value when a specific method is called
		when( dao.retrieveUser("cortazar") ).thenReturn( null );
		User u = new User ("cortazar", "cortazar",false);
		db.registerUser(u);
		
		//Use ArgumentCaptor to capture argument values for further assertions.
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		
		// Setting expectations -  the method storeUser() is called once and the argument is intercepted
		verify (dao).storeUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		logger.info("Registering mock new user: " + newUser.getEmail());
	
		assertEquals( "cortazar", newUser.getEmail());
		logger.debug("Finishing testRegisterUserCorrectly() ");
	}
	
	@Test
	public void testRegisterUserAlreadyExists() {
		User u = new User("cortazar","cortazar",false);
		User u1 = new User("cortazar","dipina",false);
		when( dao.retrieveUser("cortazar") ).thenReturn(u);
		// When the user exist, we update the password
		db.registerUser(u1);
		
		
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (dao).updateUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		logger.info("Changing password of mock user: " + newUser.getPassword());
		assertEquals( "dipina", newUser.getPassword());
		
	}
	@Test
	public void testAddBookValid() throws RemoteException {
		// Setting up the test data
		
		Book b =new Book(2,"maria","pabloaut",0.2);
		
		//Stubbing
		when( dao.retrieveBook (2) ).thenReturn(null);
		
		//Calling the method under test
		
		db.addBookToDb(b);
		
		// Verifying the outcome
		ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass( Book.class );
		verify (dao).storeBook(bookCaptor.capture());
		Book newBook = bookCaptor.getValue();
		
		assertEquals( b.toString(), newBook.toString());
		
	}
	@Test(expected=RemoteException.class)
	public void testAddBookInvalidRemote() throws RemoteException {
		// Setting up the test data
		
		Book b =null;
		
		//Stubbing
		//when( dao.retrieveBook (a) ).thenReturn(null);
		
		logger.error("Invalid book remote, testing exception");
		IRemote remote = new Remote();
		
		//Calling the method under test
		remote.addBook(b);
	}

	/**
	@Test(expected=AssertionError.class)
	public void testAddBookInvalidDB() throws RemoteException {
		// Setting up the test data
		
		Book b =null;
		
		//Stubbing
		//when( dao.retrieveBook (a) ).thenReturn(null);
		
		logger.info("Invalid book remote, testing exception");
		
		
		//Calling the method under test
		db.addBookToDb(b);
	}
/**
	@Test(expected=RemoteException.class)
	public void testSayMessageUserInvalid() throws RemoteException {
		
		when( dao.retrieveUser("cortazar") ).thenReturn( null );
		logger.info("Say message and invalid user, testing exception");
		
		db.
			
	}
	
	@Test
	public void testSayMessageUserValid() throws RemoteException {
		// Setting up the test data
		User u = new User("cortazar","cortazar");
		Message mes = new Message("testing message");
		mes.setUser(u);
		u.addMessage(mes) ;
		
		//Stubbing
		when( dao.retrieveUser("cortazar") ).thenReturn(u);
		
		//Calling the method under test
		
		m.sayMessage("cortazar", "cortazar", "testing message");
		
		// Verifying the outcome
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (dao).updateUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		
		assertEquals( "cortazar", newUser.getMessages().get(0).getUser().getLogin());
		
	}
	*/

}