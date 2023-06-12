package ru.kovalev.homelibraryboot.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.kovalev.homelibraryboot.models.Book;
import ru.kovalev.homelibraryboot.services.LibrarianServise;

@Component
public class BookValidator implements Validator {

	private String bookTitle = "Убить пересмешника";
	private String bookAuthor = "Харпер Ли";
	private String bookLaguage = "русский";
	private String bookDescription = "История судебного процесса по делу чернокожего парня, обвиненного в насилии над белой девушкой.";

	private final LibrarianServise librarianServise;

	public BookValidator(LibrarianServise librarianServise) {
		this.librarianServise = librarianServise;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Book.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Book book = (Book) target;

		if (!book.getTitle().equals(bookTitle)) {
			errors.rejectValue("title", "", "Title only: Убить пересмешника");
		}
		if (!book.getAuthor().equals(bookAuthor)) {
			errors.rejectValue("author", "", "Author only: Харпер Ли");
		}
		if (!book.getDescription().equals(bookDescription)) {
			errors.rejectValue("description", "",
					"Description only: История судебного процесса по делу чернокожего парня, обвиненного в насилии над белой девушкой.");
		}
		if (!book.getLanguage().equals(bookLaguage)) {
			errors.rejectValue("language", "", "Language only: русский");
		}

		if (librarianServise.findBookByTitle(book.getTitle()).isPresent()) {
			errors.rejectValue("title", "", "This book is already taken");
		}
	}

}
