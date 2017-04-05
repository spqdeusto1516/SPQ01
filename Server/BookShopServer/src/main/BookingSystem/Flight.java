package BookingSystem;
import java.util.List;
import java.util.ArrayList;


import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Flight {

	@PrimaryKey
	private int flightCode;
	private int airlineCode;
	private int totalSeats;
	private int remainingSeats;
	
	private String destination;
	private String departure;

	public Flight(int flightCode, int airlineCode, String dest,String orig, int tS, int remaining ) {
		this.flightCode= flightCode;
		this.airlineCode=airlineCode;
		this.destination=dest;
		this.departure=orig;
		this.totalSeats=tS;
		this.remainingSeats=remaining;
	}

	public int getFlightCode() {
		return flightCode;
	}

	public int getAirlineCode() {
		return airlineCode;
	}

	public String getDestination() {
		return destination;
	}

	public String getDeparture() {
		return departure;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public int getRemainingSeats() {
		return remainingSeats;
	}
	
}