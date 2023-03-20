package ru.kovalev.homelibraryboot.services;

import java.util.Optional;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.kovalev.homelibraryboot.models.Person;
import ru.kovalev.homelibraryboot.repositories.PeopleRepository;
import ru.kovalev.homelibraryboot.security.PersonDetails;

@Service
public class PersonsDetailService implements UserDetailsService{

	private final PeopleRepository peopleRepository;
	

	public PersonsDetailService(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional <Person> person = peopleRepository.findByUserName(username);
		if(person.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		return new PersonDetails(person.get());
	}

}
