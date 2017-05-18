	package server;

	import static org.junit.Assert.assertTrue;

	import java.util.Random;

	import org.databene.contiperf.PerfTest;
	import org.databene.contiperf.Required;
	import org.databene.contiperf.junit.ContiPerfRule;
	import org.junit.Rule;
	import org.junit.Test;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;

	import db.DB;
	import db.IDB;
	import junit.framework.JUnit4TestAdapter;
	import server.data.Book;

	public class ContiPerfTest {
		IDB db =new DB();
		
		int count=50;
		int count2=50;
		int count3=50;
		
		String a ="" + count ;
		String b ="" + count +1 ;
		String c ="" + count +2;
		
		Random rand = new Random();
		final static Logger logger = LoggerFactory.getLogger(ContiPerfTest.class);
		
		@Rule public  ContiPerfRule rule = new ContiPerfRule();
		
		public static junit.framework.Test suite() {
			return new JUnit4TestAdapter(ContiPerfTest.class);
		}
		
		
		@Test
		@PerfTest(invocations = 250)
		public void storeBook() {
			logger.info("ContiPerf store Book");
			
			Book b =new Book(count,"Conti", "probando",10.3);
			
			db.addBookToDb(b);
			count++;
		}

		
		@Test
		@PerfTest(invocations = 50)
		@Required(average = 100, throughput =10, max =2)
		public void registerNewUserTest() {
			
			String idEmail ="" + count3 ;
				logger.info("Test 1 - Register new user");
				db.registerUser(idEmail, "ipina",false);
				logger.info("User email" + idEmail);
				
				count3++;
			
			
			/*
			 * Very simple test, inserting a valid new user
			 */
		
			assertTrue( true );
		}
		@Test
		@PerfTest(duration = 5000)
		@Required(average = 100, throughput =10)
		public void storeBookDuration() {
			logger.info("ContiPerf  storeBookDuration");
			
			Book b =new Book(count,"Conti", "probando",10.3);
			
			db.addBookToDb(b);
			count++;
		}
		
		@Test
		@PerfTest(threads = 40, duration = 1000)
		public void showBookThreads() {
			logger.info("ContiPerf showBookThreads");
			
			
			
			db.showBookByISBN(50);
			
		}
		
		
		
		
		@Test
		@PerfTest(threads = 10, duration = 5000)
		public void showBookThreads2() {
			logger.info("ContiPerf store Book");
			
			
			
			db.showBookByISBN(60);
			count2++;
		}

		

	}


