package ru.kovalev.homelibraryboot.services;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import ru.kovalev.homelibraryboot.models.Person;
import ru.kovalev.homelibraryboot.repositories.PeopleRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
class RegistrationServiceTest {

	@Autowired
	private RegistrationService registrationService;
	
	@MockBean
	private PeopleRepository peopleRepository;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
	@Test
	void testRegistrationOneAdmin() {
		Person person = new Person("testAdminNameForTests");
		person.setPassword("1111111111");
		registrationService.registrationOneAdmin(person);
		Assert.assertTrue(CoreMatchers.is(person.getUserName()).matches("testAdminNameForTests"));
		Assert.assertNotNull(person.getCreatedAt());
		Assert.assertTrue(CoreMatchers.is(person.getRole()).matches("ROLE_ADMIN"));
		
		Mockito.verify(peopleRepository, Mockito.times(1)).save(person);
		Mockito.verify(passwordEncoder, Mockito.times(1)).encode(ArgumentMatchers.eq("1111111111"));
	}
	
	@Test
	void testRegistration() {
		Person person = new Person("testUserNameForTests");
		person.setPassword("2222222222");
		registrationService.registration(person);
		Assert.assertTrue(CoreMatchers.is(person.getUserName()).matches("testUserNameForTests"));
		Assert.assertNotNull(person.getCreatedAt());
		Assert.assertTrue(CoreMatchers.is(person.getRole()).matches("ROLE_USER"));
		
		Mockito.verify(peopleRepository, Mockito.times(1)).save(person);
		Mockito.verify(passwordEncoder, Mockito.times(1)).encode(ArgumentMatchers.eq("2222222222"));
	}

}
