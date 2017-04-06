package dao;

public class DaoFactory {

	IDAO reservationDao;
	IDAO userDao;
	
	public DaoFactory() {}

	public IDAO creatDao(String type){
		if(type.equals("User")) {
			return userDao=new UserDAO();
		}else if(type.equals("Reservation")){
			return reservationDao =new ReservationDAO();
		}
		
		return null;
	}
	
}
