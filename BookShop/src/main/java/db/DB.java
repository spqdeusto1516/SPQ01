package db;

import java.util.List;

import server.data.*;


public class DB implements IDB{

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	IDAO dao;

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
	public boolean buyBook(String u, String book_title) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createReview(String u, String b, Review r) {

			
			User user =null;
			Book book =null;
			Review review = null ;
			boolean ret=true;
			
						try {

						book= dao.retrieveBookByParameter(b);
						user = dao.retrieveUser(u);
						review = dao.retrieveReview(r.getId_review());

					} catch (Exception  e) {
						System.out.println("Exception launched in checking if the data already exist: " + e.getMessage());
						ret=false;
					}


					if (book == null || user == null || review !=null ) {



					}else if (book !=null && user != null && review == null  ){

						r.setBook(book);
						book.addReview(r);
						r.setUser(user);
						user.addReview(r);

						dao.storeReview(r);
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
			System.out.println("Exception launched: " + e.getMessage());
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

	@Override
	public boolean addBookToDb(Book b) {
		// TODO Auto-generated method stub
		Book book = null;
		
		boolean ret=true;

		try {

			book  = dao.retrieveBook(b.getISBN());
		

		} catch (Exception  e) {
			System.out.println("Exception launched in checking if the data already exist: " + e.getMessage());
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
		// ao.retrieveLicenseByName(name);
		return r;
	}

	@Override
	public User showUser(String email) {
		// TODO Auto-generated method stub
		User u=dao.retrieveUser(email);
		// ao.retrieveLicenseByName(name);
		return u;
	}

}
