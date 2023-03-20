package ru.kovalev.homelibraryboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import ru.kovalev.homelibraryboot.models.Book;


public interface BooksRepository extends JpaRepository<Book, Integer> {
	
	List<Book> findByTitle(String title);
	
	List<Book> findByTitleContaining(String title);
	
	
	
}
