package ru.kovalev.homelibraryboot.util;





import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.kovalev.homelibraryboot.models.Person;
import ru.kovalev.homelibraryboot.services.PeopleService;



@Component
public class PersonValidator implements Validator{

	private final PeopleService peopleService;


	public PersonValidator(PeopleService peopleService) {
		this.peopleService = peopleService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Person person = (Person) target;
		
		if (peopleService.findPersonByName(person.getUserName()).isPresent()) {
			errors.rejectValue("userName", "", "This user name is already taken");
		}
	}
	
	
	
}
