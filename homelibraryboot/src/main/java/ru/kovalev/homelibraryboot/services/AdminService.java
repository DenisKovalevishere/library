package ru.kovalev.homelibraryboot.services;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kovalev.homelibraryboot.models.Person;
import ru.kovalev.homelibraryboot.repositories.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class AdminService {

	private final PeopleRepository peopleRepository;
	private final PasswordEncoder passwordEncoder;

	public AdminService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
		this.peopleRepository = peopleRepository;
		this.passwordEncoder = passwordEncoder;
	}

//	@PreAuthorize("hasRole('ROLE_ADMIN')") //or hasRole('ROLE_USER')")
	public List<Person> findAllPeople() {
		return peopleRepository.findAll(Sort.by("role", "createdAt", "userName"));
	}

	public Person findPerson(int id) {
		return peopleRepository.findById(id).orElse(null); // NotFoundExeption
	}

	public Optional<Person> findPersonByName(String userName) {
		return peopleRepository.findByUserName(userName); // NotFoundExeption
	}

	public Optional<Person> findPersonByRole(String role) {
		return peopleRepository.findByRole(role);
	}

	// Add created date for new person
	private void enrichPerson(Person person) {
		person.setCreatedAt(LocalDateTime.now());
		person.setRole("ROLE_LIBRARIAN");
	}

	@Transactional
	public void savePerson(Person person) {
		enrichPerson(person);
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		peopleRepository.save(person);
	}

	@Transactional
	public void deletePerson(int id) throws Exception {
		if (findPerson(id).getRole().equals("ROLE_ADMIN")) {
			throw new Exception("Admin can't be deleted");
		} else {
			peopleRepository.deleteById(id);
		}
	}

	@Transactional
	// Method enrich only for new person, this person already exists
	public void updatePerson(Person updatedPerson, int id) {
		updatedPerson.setPassword(passwordEncoder.encode(updatedPerson.getPassword()));
		enrichPerson(updatedPerson);
		if (findPerson(id).getRole().equals("ROLE_ADMIN")) {
			updatedPerson.setRole("ROLE_ADMIN");
		}

		updatedPerson.setId(id);
		peopleRepository.save(updatedPerson);
	}

}
