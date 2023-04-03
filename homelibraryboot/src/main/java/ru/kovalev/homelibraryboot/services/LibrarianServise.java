package ru.kovalev.homelibraryboot.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kovalev.homelibraryboot.models.Book;
import ru.kovalev.homelibraryboot.models.Person;
import ru.kovalev.homelibraryboot.repositories.BooksRepository;
import ru.kovalev.homelibraryboot.repositories.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class LibrarianServise {

	private final BooksRepository booksRepository;
	private final PeopleRepository peopleRepository;



	public LibrarianServise(BooksRepository booksRepository, PeopleRepository peopleRepository) {
		this.booksRepository = booksRepository;
		this.peopleRepository = peopleRepository;

	}
	
	public List<Book> findAllBooks(){
		return booksRepository.findAll();
	}
	
	public Book findBookById(int id) {
		return booksRepository.findById(id).orElse(null);
	}
	
	public List<Book> findBookContainTitle(String title) {
		return booksRepository.findByTitleContaining(title);
	}
	
	public Optional<Book> findBookByTitle(String title) {
		return booksRepository.findByTitle(title);
	}
	
	public Person getCurrentPerson(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Person person = peopleRepository.findByUserName(auth.getName()).orElse(null);
		return person;
	}
	
	//Add created date for new book
	private void enrichBook(Book book) {
		book.setCreatedAt(LocalDateTime.now());
		book.setCreatedWho(getCurrentPerson());// created who?
		
	}
	
	@Transactional
	public void saveBook(Book book) {
		enrichBook(book);
		booksRepository.save(book);
	}
	
	@Transactional
	public void deleteBook(int id) {
		booksRepository.deleteById(id);
	}
	
	//Method enrich only for new book, this book already exists
	@Transactional
	public void updateBook(Book updatedBook, int id) {
		enrichBook(updatedBook);
		updatedBook.setId(id);
		booksRepository.save(updatedBook);
	}
	
}
