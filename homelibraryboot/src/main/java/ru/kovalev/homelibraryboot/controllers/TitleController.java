package ru.kovalev.homelibraryboot.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kovalev.homelibraryboot.dto.PersonDTO;
import ru.kovalev.homelibraryboot.models.Person;
import ru.kovalev.homelibraryboot.services.LibrarianServise;

@Controller
@RequestMapping("/library")
public class TitleController {

	private final LibrarianServise librarianServise;
	private final ModelMapper modelMapper;

	public TitleController(LibrarianServise librarianServise, ModelMapper modelMapper) {
		this.librarianServise = librarianServise;
		this.modelMapper = modelMapper;
	}

	private PersonDTO convertToPersonDTO(Person person) {
		return modelMapper.map(person, PersonDTO.class);
	}

	@GetMapping
	public String homePage() {
		return "parts/library";
	}

	@GetMapping("/redcon")

	public String redirectControl() {
		PersonDTO personDTO = convertToPersonDTO(librarianServise.getCurrentPerson());

		if (personDTO == null) {
			return "redirect:/library";
		}

		if (personDTO.getRole().equals("ROLE_ADMIN")) {
			return "redirect:/people";
		}
		if (personDTO.getRole().equals("ROLE_LIBRARIAN")) {
			return "redirect:/books";
		}
		if (personDTO.getRole().equals("ROLE_USER")) {
			return "redirect:/info";
		}
		return "redirect:/library";

	}
}
