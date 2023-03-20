package ru.kovalev.homelibraryboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import ru.kovalev.homelibraryboot.models.Person;


public interface PeopleRepository extends JpaRepository<Person, Integer>{
		
	Optional<Person> findByUserName(String userName);
	
	Optional<Person> findByRole(String role);
}
