package ru.kovalev.homelibraryboot.tasks;

import java.util.Optional;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import ru.kovalev.homelibraryboot.controllers.LibrarianController;
import ru.kovalev.homelibraryboot.models.Book;
import ru.kovalev.homelibraryboot.services.LibrarianServise;


@EnableScheduling
public class ScheduledTasks {

	private final LibrarianServise librarianServise;
	private String foundBookTitle = "Убить пересмешника";

	
	
	public ScheduledTasks(LibrarianServise librarianServise) {
		this.librarianServise = librarianServise;
	}

	@Scheduled()
	public void deleteOldBook () {
		Optional<Book> book = librarianServise.findBookByTitle(this.foundBookTitle);
		if(book.isPresent()) {
		librarianServise.deleteBook(book.get().getId());
		}
	}
	
}
