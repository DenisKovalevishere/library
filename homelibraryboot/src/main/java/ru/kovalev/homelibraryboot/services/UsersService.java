package ru.kovalev.homelibraryboot.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kovalev.homelibraryboot.models.Book;
import ru.kovalev.homelibraryboot.models.InformationBookPerson;
import ru.kovalev.homelibraryboot.repositories.BooksRepository;
import ru.kovalev.homelibraryboot.repositories.InformationsBookPersonRepository;
import ru.kovalev.homelibraryboot.repositories.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class UsersService {

	private final InformationsBookPersonRepository informationsBookPersonRepository;
	private final BooksRepository booksRepository;
	private final LibrarianServise librarianServise;

	
	public UsersService(InformationsBookPersonRepository informationsBookPersonRepository, BooksRepository booksRepository, PeopleRepository peopleRepository, LibrarianServise librarianServise) {
		this.informationsBookPersonRepository = informationsBookPersonRepository;
		this.booksRepository = booksRepository;
		this.librarianServise = librarianServise;
		
	}
	
	public List <InformationBookPerson> findAllInformation() {
		return informationsBookPersonRepository.findAll();
	}
	
	public InformationBookPerson findInformation(int id) {
		return informationsBookPersonRepository.findById(id).orElse(null);
	}
	
	//show my information about books
	public List<InformationBookPerson> findMyInformation(){
		return informationsBookPersonRepository.findByRidingPerson(librarianServise.getCurrentPerson()).stream()
				.sorted((o1, o2) -> ((o1.getReadBook().getTitle().compareTo(o2.getReadBook().getTitle())))).collect(Collectors.toList());
	}
	
	//show my information about readed books
	public List<InformationBookPerson> findMyInformationReadedBook(){
		List<InformationBookPerson> allMyInfo = findMyInformation();
		List<InformationBookPerson> myInformationReadBook = new ArrayList<>();
		
		for(int i = 0; i<allMyInfo.size(); i++) {
			if (findMyInformation().get(i).isRead().equals(true)) {
				myInformationReadBook.add(findMyInformation().get(i));
			}
		}		
		return myInformationReadBook;
	}
	
	
	//check i read this book
	@Transactional
	public void setReadingWhatBook(int booksId) {	
		InformationBookPerson information = new InformationBookPerson();
		Book foundBook = booksRepository.findById(booksId).orElse(null);
		information.setReadBook(foundBook);
		information.setRidingPerson(librarianServise.getCurrentPerson());
		information.setCurentPage(0);
		information.setStartReading(LocalDateTime.now());
		information.setRead(false);
		information.setEndReading(null);
		informationsBookPersonRepository.save(information);
	}
	
	
	//Set current page for reading book
	@Transactional
	public void setCurrentPage(int id, int currentPage) {
		
		
		InformationBookPerson information = findInformation(id);
		
		information.setCurentPage(currentPage);
			
		information.setRead(false);
		information.setEndReading(null);
		informationsBookPersonRepository.save(information);
	}
	

	
	//Set book readed to the end or read to the start
	@Transactional
	public void setReadBook(int id) {
		InformationBookPerson information = informationsBookPersonRepository.findById(id).orElse(null);
		boolean readed;
		if(information.isRead().equals(false)) {
			readed = true;
		} else {
			readed = false;
		}
		
		information.setRead(readed);
		
		if (readed) {
			information.setCurentPage(null);
			information.setEndReading(LocalDateTime.now());
		} else {
			information.setCurentPage(0);
			information.setStartReading(LocalDateTime.now());
			information.setEndReading(null);
		}
		
		informationsBookPersonRepository.save(information);
	}
	
	@Transactional
	public void deleateInformation(int id) {
		informationsBookPersonRepository.deleteById(id);
	}
	
	
}
