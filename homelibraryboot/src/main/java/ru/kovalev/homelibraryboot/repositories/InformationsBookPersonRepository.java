package ru.kovalev.homelibraryboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import ru.kovalev.homelibraryboot.models.InformationBookPerson;
import ru.kovalev.homelibraryboot.models.Person;




public interface InformationsBookPersonRepository extends JpaRepository<InformationBookPerson, Integer>{

	public List<InformationBookPerson> findByRidingPerson(Person person);
		
	
	
}
