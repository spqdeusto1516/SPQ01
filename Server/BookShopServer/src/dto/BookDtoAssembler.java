package dto;

import java.util.ArrayList;
import java.util.List;

import bookShopSystem.Book;

public class BookDtoAssembler {

	public List<BookDTO> assemble(Book[] books) {
		List<BookDTO> booksDTO = new ArrayList<BookDTO>();

		for (Book t : books) {
//			booksDTO.add(new BookDTO(""+t.getAirlineCode(), t.getDeparture()+t.getDestination()));
			booksDTO.add(new BookDTO(acronym, description))
		}
		System.out.println("* Assembling Flights ...");
		
		return booksDTO;
	}
}
