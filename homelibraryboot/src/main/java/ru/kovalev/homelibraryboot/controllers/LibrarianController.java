package ru.kovalev.homelibraryboot.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.kovalev.homelibraryboot.dto.BookDTO;
import ru.kovalev.homelibraryboot.models.Book;
import ru.kovalev.homelibraryboot.services.LibrarianServise;
import ru.kovalev.homelibraryboot.services.UsersService;

@Controller
@RequestMapping("/books")
public class LibrarianController {
	
	private final LibrarianServise librarianServise;
	private final UsersService usersService;
	private final ModelMapper modelMapper;


	public LibrarianController(LibrarianServise librarianServise, ModelMapper modelMapper, UsersService usersService) {
		this.librarianServise = librarianServise;
		this.usersService = usersService;
		this.modelMapper = modelMapper;
	}
	
	private Book convertToBook(BookDTO bookDTO) {
		return this.modelMapper.map(bookDTO, Book.class);
	}
	
//	private BookDTO convertToBookDTO(Book book) {
//		return this.modelMapper.map(book, BookDTO.class);
//	}
	@PreAuthorize("hasRole('ROLE_LIBRARIAN') or hasRole('ROLE_USER')")
	@GetMapping
	public String index(Model model) {
		model.addAttribute("books", librarianServise.findAllBooks());
		return "books/index";
	}
	////	@PreAuthorize("hasRole('ROLE_LIBRARIAN') or hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public String showBook(Model model, @PathVariable ("id") int id) {
		model.addAttribute("book", librarianServise.findBookById(id));
		return "books/show";
	}
	
//	@PreAuthorize("hasRole('ROLE_LIBRARIAN')")
	@GetMapping("/new")
	public String addBook(@ModelAttribute ("book") BookDTO bookDTO) {
		return "books/new";
	}
	
	@PreAuthorize("hasRole('ROLE_LIBRARIAN')")
	@PostMapping
	public String createBook(@ModelAttribute ("book") @Valid BookDTO bookDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "books/new";
		}
		librarianServise.saveBook(convertToBook(bookDTO));		
		return "redirect:/books";
	}
	
//	@PreAuthorize("hasRole('ROLE_LIBRARIAN')")
	@GetMapping("/{id}/edit")
	public String editBook(Model model, @PathVariable ("id") int id) {
		model.addAttribute("book", librarianServise.findBookById(id));
		return "books/edit";
	}

	@PreAuthorize("hasRole('ROLE_LIBRARIAN')")
	@PatchMapping("{id}")
	public String updateBook(@ModelAttribute ("book") @Valid BookDTO bookDTO, @PathVariable ("id") int id, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "books/edit";
		}
		librarianServise.updateBook(convertToBook(bookDTO), id);
		
		return "redirect:/books";
	}
	
	//patch or post ; need relocation to userController
	////	@PreAuthorize("hasRole('ROLE_USER')")
	@PatchMapping("{id}/check")
	public String checkBook(@PathVariable ("id") int id) {
		usersService.setReadingWhatBook(id);
		return "redirect:/info/";
	}
	
	@PreAuthorize("hasRole('ROLE_LIBRARIAN')")
	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable ("id") int id) {
		librarianServise.deleteBook(id);
		return "redirect:/books";
	}
	
	////	@PreAuthorize("hasRole('ROLE_LIBRARIAN') or hasRole('ROLE_USER')")
	@GetMapping("/search")
	public String searchBook(@ModelAttribute ("query") String query) {
		return "books/search";
	}
	
	////	@PreAuthorize("hasRole('ROLE_LIBRARIAN') or hasRole('ROLE_USER')")
	@PostMapping("/search")
	public String searchByTitle(Model model, @RequestParam ("query") String query) {
		model.addAttribute("books", librarianServise.findBookByTitle(query));
		return "books/search";
	}
}
