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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ru.kovalev.homelibraryboot.models.Person;
import ru.kovalev.homelibraryboot.repositories.PeopleRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
class AdminServiceTest {

	@Autowired
	private AdminService peopleService;

	@MockBean
	private PeopleRepository peopleRepository;

	@MockBean
	private PasswordEncoder passwordEncoder;

	@Test
	void testSavePerson() {
		Person person = new Person("testUserNameForTests");
		person.setPassword("1111111111");
		peopleService.savePerson(person);
		Assert.assertTrue(CoreMatchers.is(person.getUserName()).matches("testUserNameForTests"));
		Assert.assertNotNull(person.getCreatedAt());
		Assert.assertTrue(CoreMatchers.is(person.getRole()).matches("ROLE_LIBRARIAN"));

		Mockito.verify(peopleRepository, Mockito.times(1)).save(person);
		Mockito.verify(passwordEncoder, Mockito.times(1)).encode(ArgumentMatchers.eq("1111111111"));
	}

}
