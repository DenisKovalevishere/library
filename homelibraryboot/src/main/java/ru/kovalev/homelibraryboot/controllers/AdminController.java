package ru.kovalev.homelibraryboot.controllers;




import java.util.stream.Collectors;

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


import ru.kovalev.homelibraryboot.dto.PersonDTO;
import ru.kovalev.homelibraryboot.models.Person;
import ru.kovalev.homelibraryboot.services.PeopleService;
import ru.kovalev.homelibraryboot.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class AdminController {

	private final PeopleService peopleService;
	private final ModelMapper modelMapper;
	private final PersonValidator personValidator;

	
	public AdminController(PeopleService peopleService, ModelMapper modelMapper, PersonValidator personValidator) {
		this.peopleService = peopleService;
		this.modelMapper = modelMapper;
		this.personValidator = personValidator;
	}
	
	private Person convertToPerson(PersonDTO personDTO) {
		return this.modelMapper.map(personDTO, Person.class);
	}
	
	private PersonDTO convertToPersonDTO(Person person) {
		return this.modelMapper.map(person, PersonDTO.class);
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping()
	public String index(Model model){
		model.addAttribute("people", peopleService.findAllPeople().stream().map(this::convertToPersonDTO).collect(Collectors.toList()));
		return "people/index";
	}
	
	@GetMapping("/{id}")
	public String showPerson(Model model, @PathVariable ("id") int id) {
		model.addAttribute("person", convertToPersonDTO(peopleService.findPerson(id)));
		return "people/show";
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/new")
	public String newLibrarian(@ModelAttribute("person") PersonDTO personDTO) {
		return "people/new";
	}
	
////	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public String saveLibrarian(@ModelAttribute("person") @Valid PersonDTO personDTO, BindingResult bindingResult) {
		Person person = convertToPerson(personDTO);
		personValidator.validate(person, bindingResult);
		if(bindingResult.hasErrors()) {
			return "people/new";
		}
		
		peopleService.savePerson(person);
		return "redirect:/people";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("person", convertToPersonDTO(peopleService.findPerson(id)));
		return "people/edit";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PatchMapping("/{id}")
	public String updatePerson(@ModelAttribute ("person") @Valid PersonDTO personDTO, @PathVariable ("id") int id, BindingResult bindingResult) {
		Person person = convertToPerson(personDTO);
		
		personValidator.validate(person, bindingResult);
		if (bindingResult.hasErrors()) {
			return "people/edit";
		}
		peopleService.updatePerson(person, id);
		
		return "redirect:/people";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public String deletePerson(@PathVariable ("id") int id) throws Exception {
		peopleService.deletePerson(id);
		return "redirect:/people";
	}
	
}
