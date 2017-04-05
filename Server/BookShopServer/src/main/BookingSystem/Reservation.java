package BookingSystem;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reservation {


	@PrimaryKey
	private int reservationCode;
	private float price;
	private Date bookingDate;
	private int seatNumber;
	private int numberOfLuggages;
	
	private User user;
	private Flight flight;
	
	@Join
	private List<Passenger> passengers;

	public Reservation(int reservationCode, int seatNumber,Date time, int numberOfL,float price) {
		passengers= new ArrayList<Passenger>();
		this.reservationCode= reservationCode;
		this.bookingDate=time;
		this.numberOfLuggages=numberOfL;
		this.price=price;
		this.seatNumber=seatNumber;		
	}

	public int getReservationCode() {
		System.out.println("res");
		return reservationCode;
	}

	public Flight getFlight() {
		return flight;
	}

	public float getPrice() {
		return price;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public int getNumberOfLuggages() {
		return numberOfLuggages;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassenger(Passenger passenger) {
		this.passengers.add(passenger);
	}
	
}