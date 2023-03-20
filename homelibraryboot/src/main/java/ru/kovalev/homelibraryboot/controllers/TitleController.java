package ru.kovalev.homelibraryboot.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kovalev.homelibraryboot.models.Person;
import ru.kovalev.homelibraryboot.services.LibrarianServise;

@Controller
@RequestMapping("/library")
public class TitleController {

	private final LibrarianServise librarianServise;
	

	public TitleController(LibrarianServise librarianServise) {
		this.librarianServise = librarianServise;
	}


	@GetMapping
	public String redirectControl() {
		Person person = librarianServise.getCurrentPerson();
		
		if(person.getRole().equals("ROLE_ADMIN")) {
			return "redirect:/people";
		} if(person.getRole().equals("ROLE_LIBRARIAN")) {
			return "redirect:/books";
		} else {
			return "redirect:/info";
		}
		
	}
	
}
