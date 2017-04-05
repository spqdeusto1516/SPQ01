package dto;

import java.util.ArrayList;
import java.util.List;

import BookingSystem.Flight;

public class FlightDtoAssembler {

	public List<FlightDTO> assemble(Flight[] flights) {
		List<FlightDTO> flightsDTO = new ArrayList<>();

		for (Flight t : flights) {
			flightsDTO.add(new FlightDTO(""+t.getAirlineCode(), t.getDeparture()+t.getDestination()));
		}
		System.out.println("* Assembling Flights ...");
		
		return flightsDTO;
	}
}
