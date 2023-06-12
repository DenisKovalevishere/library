package ru.kovalev.homelibraryboot.services;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kovalev.homelibraryboot.models.Person;
import ru.kovalev.homelibraryboot.repositories.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class RegistrationService {

	private final PeopleRepository peopleRepository;
	private final PasswordEncoder passwordEncoder;

	public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
		this.peopleRepository = peopleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public void registration(Person person) {
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		person.setRole("ROLE_USER");
		person.setCreatedAt(LocalDateTime.now());
		peopleRepository.save(person);
	}

	@Transactional
	public void registrationOneAdmin(Person person) {
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		person.setRole("ROLE_ADMIN");
		person.setCreatedAt(LocalDateTime.now());
		peopleRepository.save(person);
	}
}
