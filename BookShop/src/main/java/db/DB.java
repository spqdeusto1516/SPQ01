package db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import server.data.*;


public class DB implements IDB{

	private static final long serialVersionUID = 1L;
	IDAO dao;
	final static  Logger logger = LoggerFactory.getLogger(DB.class);

	public DB(){
		super();
		dao = new DAO();

	}

	public DB(IDAO udao) {
		super();
		dao = udao;

	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return dao.getAllUsers();
	}

	@Override
	public List<Review> getAllReviews() {
		// TODO Auto-generated method stub
		return dao.getAllReviews();
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return dao.getAllBooks();
	}

	@Override
	public boolean buyBook(String email, String book_title) {
		// TODO Auto-generated method stub
		Book book =null;		
		User user =null;
		boolean ret=true;
		double price;
		try {
			book= dao.retrieveBookByParameter(book_title);									
			user = dao.retrieveUser(email);					
		} catch (Exception  e) {
			logger.error("Exception launched in checking if the data already exist: ");
			logger.trace(e.getMessage());
			e.printStackTrace();
			ret=false;
		}
		if(user.getMoney()>=book.getPrice()){

			if (book == null  || user ==null ) {

			}else if (book !=null  &&  user != null  ){
				book.addUser(user);										
				user.addBook(book);	

				price=book.getPrice();

				user.setMoney(user.getMoney()-price);

				dao.updateBook(book);
				dao.updateUser(user);

			}	

		}
		else{
			logger.error("Price higher than your balance");
		}


		return ret;

	}


	public boolean addReview(Book b, Review r, User u ) {
		Book book =null;
		Review review = null ;
		User user =null;
		boolean ret=true;

		try {

			book= dao.retrieveBookByParameter(b.getTitle());				
			review = dao.retrieveReview(r.getId_review());
			user = dao.retrieveUser(u.getEmail());


		} catch (Exception  e) {
			logger.error("Exception launched in checking if the data already exist: ");
			e.printStackTrace();
			ret=false;
		}


		if (book == null  || user == null ||review !=null ) {



		}else if (book !=null  && user !=null && review == null  ){
			int a= getAllReviews().size()+1;
			r.setBook(book);
			r.setUser(user);
			r.setId_review(a);

			book.addReview(r);										
			user.addReview(r);


			dao.updateBook(book);
			dao.updateUser(user);

		}

		return ret;	
	}





	@Override
	public boolean registerUser(String email, String password, boolean role) {
		// TODO Auto-generated method stub
		User user = null;
		boolean ret=true;

		try {
			user = dao.retrieveUser(email);
		} catch (Exception  e) {
			logger.error("Exception launched: ");
			logger.trace(e.getMessage());
			e.printStackTrace();
			ret=false;
		}

		if (user != null) {
			user.setPassword(password);
			user.setRole(role);

			dao.updateUser(user);

		} else {

			user = new User(email, password,role);
			dao.storeUser(user);

		}
		return ret;
	}

	public boolean registerUser(User u) {


		User user = null;
		boolean ret=true;

		try {
			user = dao.retrieveUser(u.getEmail());
		} catch (Exception  e) {
			logger.error("Exception launched: ");
			logger.trace(e.getMessage());
			e.getStackTrace();
			ret=false;
		}

		if (user != null) {

			user.setPassword(u.getPassword());
			user.setRole(u.getRole());

			dao.updateUser(user);

		} else {


			dao.storeUser(u);

		}
		return ret;
	}

	@Override
	public boolean addBookToDb(Book b) {
		// TODO Auto-generated method stub
		Book book = null;

		boolean ret=true;

		try {

			book  = dao.retrieveBook(b.getISBN());


		} catch (Exception  e) {
			logger.error("Exception launched in checking if the data already exist: ");
			logger.trace(e.getMessage());
			e.getStackTrace();
			ret = false;
		}

		if (book != null ) {

		}else{



			dao.storeBook(b);


		}

		return ret;
	}

	@Override
	public Book showBookByISBN(int ISBN) {
		// TODO Auto-generated method stub
		Book b=dao.retrieveBook(ISBN);
		// ao.retrieveLicenseByName(name);
		return b;
	}

	@Override
	public Book showBookByTitle(String title) {
		// TODO Auto-generated method stub
		Book b=dao.retrieveBookByParameter(title);
		// ao.retrieveLicenseByName(name);
		return b;
	}

	@Override
	public Review showReview( int id_review) {
		// TODO Auto-generated method stub
		Review r=dao.retrieveReview(id_review);

		return r;
	}

	@Override
	public User showUser(String email) {
		// TODO Auto-generated method stub
		User u=dao.retrieveUser(email);
		// ao.retrieveLicenseByName(name);
		return u;
	}
	public List<Review> getUserReviews(String email){
		User u=showUser(email);
		List<Review> userReviews=u.getReviews();
		return userReviews;

	}
	public List<Review> getBookReviews(String title){
		Book b= showBookByTitle(title);
		List<Review> bookReviews=b.getReviews();
		return bookReviews;
	}
	public double averageRatingByBook(String title){
		List<Review> bookReviews=null;
		bookReviews=getBookReviews(title);
		double total=0;


		for(Review r : bookReviews){
			total=total+r.getRating();

		}
		return total/bookReviews.size();
	}
	public double averageRatingByUser(String email){
		List<Review> userReviews=null;
		userReviews=getUserReviews(email);
		double total=0;


		for(Review r : userReviews){
			total=total+r.getRating();

		}
		return total/userReviews.size();
	}
	public boolean deleteReview(int id_review){
		boolean ret=false;
		Review r;

		r =showReview(id_review);
		if(r!=null){

			dao.deleteReview(r);


			ret=true;}
		else{

		}


		return ret;


	}
	public boolean deleteBook(int ISBN){
		boolean ret=false;
		Book b;

		b =showBookByISBN(ISBN);
		if(b!=null){

			dao.deleteBook(b);


			ret=true;}
		else{

		}


		return ret;


	}

}
