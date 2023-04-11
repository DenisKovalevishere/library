package ru.kovalev.homelibraryboot.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kovalev.homelibraryboot.dto.PersonDTO;
import ru.kovalev.homelibraryboot.models.Person;
import ru.kovalev.homelibraryboot.services.AdminService;

import ru.kovalev.homelibraryboot.services.RegistrationService;
import ru.kovalev.homelibraryboot.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {

	private final RegistrationService registrationService;
	private final ModelMapper modelMapper;
	private final PersonValidator personValidator;

	private final AdminService peopleService;

	public AuthController(RegistrationService registrationService, ModelMapper modelMapper,
			PersonValidator personValidator, AdminService adminService) {
		this.registrationService = registrationService;
		this.modelMapper = modelMapper;
		this.personValidator = personValidator;

		this.peopleService = adminService;
	}

	private Person convertToPerson(PersonDTO personDTO) {
		return this.modelMapper.map(personDTO, Person.class);
	}

	@PostMapping("/registration")
	public String performRegistration(@ModelAttribute("person") @Valid PersonDTO personDTO,
			BindingResult bindingResult) {

		Person person = convertToPerson(personDTO);

		personValidator.validate(person, bindingResult);

		if (bindingResult.hasErrors()) {
			return "auth/registration";//

		}

		if (peopleService.findPersonByRole("ROLE_ADMIN").isEmpty()) {
			registrationService.registrationOneAdmin(person);
		} else {
			registrationService.registration(person);
		}
		return "redirect:/auth/login";//

	}

	@GetMapping("/login")
	public String loginPage() {
		return "auth/login";
	}

	@GetMapping("/registration")
	public String registration(@ModelAttribute("person") PersonDTO personDTO) {
		return "auth/registration";
	}

}
