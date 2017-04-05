package BookingSystem;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Passenger {

	@PrimaryKey
	int ID;
	String name;
	
	public Passenger(int id, String name){
		ID=id;
		this.name=name;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
