package ru.kovalev.homelibraryboot.controllers;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kovalev.homelibraryboot.dto.InformationBookPersonDTO;
import ru.kovalev.homelibraryboot.models.InformationBookPerson;
import ru.kovalev.homelibraryboot.services.UsersService;

@Validated
@Controller
@RequestMapping("/info")
public class UsersController {

	private final UsersService usersService;
	private final ModelMapper modelMapper;

	public UsersController(UsersService usersService, ModelMapper modelMapper) {
		this.usersService = usersService;
		this.modelMapper = modelMapper;

	}

	private InformationBookPersonDTO convertToInformationDTO(InformationBookPerson info) {
		return modelMapper.map(info, InformationBookPersonDTO.class);
	}

//	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping
	public String myIndex(Model model) {
		model.addAttribute("allInfo", usersService.findMyInformation().stream().map(this::convertToInformationDTO)
				.collect(Collectors.toList()));
		return "info/my_index";
	}

//	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public String showInfo(Model model, @PathVariable("id") int id) {
		model.addAttribute("oneInfo", convertToInformationDTO(usersService.findInformation(id)));
		return "info/show";
	}

//	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/readed")
	public String myReadedBookInfo(Model model) {
		model.addAttribute("readedInfo", usersService.findMyInformationReadedBook().stream()
				.map(this::convertToInformationDTO).collect(Collectors.toList()));
		return "info/my_readed";
	}

//	@PreAuthorize("hasRole('ROLE_USER')")
	@PatchMapping("{id}/set_page")
	public String setPage(@PathVariable("id") int id, @ModelAttribute("curentPage") Integer curentPage,
			BindingResult bindingResult) {

		usersService.setCurrentPage(id, curentPage);
		return "redirect:/info/" + id;
	}

	//// @PreAuthorize("hasRole('ROLE_USER')")
	@PatchMapping("{id}/set_readed")
	public String setReaded(@PathVariable("id") int id) {
		usersService.setReadBook(id);
		return "redirect:/info/" + id;
	}

}
