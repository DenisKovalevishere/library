package ru.kovalev.homelibraryboot.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kovalev.homelibraryboot.services.UsersService;

@Controller
@RequestMapping("/info")
public class UsersController {

	private final UsersService usersService;


	
	public UsersController(UsersService usersService) {
		this.usersService = usersService;

	}
	

//	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping
	public String myIndex(Model model) {
		model.addAttribute("allInfo",usersService.findMyInformation());
		return "info/my_index";
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public String showInfo(Model model, @PathVariable("id") int id) {
		model.addAttribute("oneInfo", usersService.findInformation(id));
		return "info/show";
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/readed")
	public String myReadedBookInfo(Model model) {
		model.addAttribute("readedInfo", usersService.findMyInformationReadedBook());
		return "info/my_readed";
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@PatchMapping("{id}/set_page")
	public String setPage(@PathVariable("id") int id, @ModelAttribute("curent_page") int curentPage) {
		usersService.setCurrentPage(id, curentPage);
		return "redirect:/info/"+id;
	}
	
	////	@PreAuthorize("hasRole('ROLE_USER')")
	@PatchMapping("{id}/set_readed")
	public String setReaded(@PathVariable("id") int id) {
		usersService.setReadBook(id);
		return "redirect:/info/"+id;
	}
	
}
