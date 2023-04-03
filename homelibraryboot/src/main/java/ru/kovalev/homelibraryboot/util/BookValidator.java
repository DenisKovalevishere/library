package ru.kovalev.homelibraryboot.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.kovalev.homelibraryboot.models.Book;
import ru.kovalev.homelibraryboot.services.LibrarianServise;

@Component
public class BookValidator implements Validator {

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

		if (librarianServise.findBookByTitle(book.getTitle()).isPresent()) {
			errors.rejectValue("title", "This book is already taken");
		}
	}

}
