package dto;

import java.io.Serializable;

public class FlightDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String acronym;
	private String description;
	//addStuff

	public FlightDTO(String acronym, String description) {
		this.acronym = acronym;
		this.description = description;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}