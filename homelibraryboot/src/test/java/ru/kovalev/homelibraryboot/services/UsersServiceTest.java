package ru.kovalev.homelibraryboot.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ru.kovalev.homelibraryboot.models.Book;
import ru.kovalev.homelibraryboot.models.InformationBookPerson;

import ru.kovalev.homelibraryboot.repositories.InformationsBookPersonRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser(username = "testnameuser", password = "1111111111")
@TestPropertySource("/application-test.properties")
class UsersServiceTest {

	@Autowired
	private UsersService usersService;

	@Autowired
	private LibrarianServise librarianServise;

	@Autowired
	private AdminService peopleService;

	@MockBean
	private InformationsBookPersonRepository informationsBookPersonRepository;

	private String bookTitle = "TestTitle";
	private String bookAuthor = "TestAuthor";
	private String bookLanguage = "TestLanguage";
	private String bookDescription = "TestDescription";

	private int createBook() {
		Book book = new Book(bookTitle, bookAuthor, bookLanguage, bookDescription);
		return book.getId();
	}

	private InformationBookPerson createInfo() {
		InformationBookPerson info = new InformationBookPerson();
		info.setId(0);
		info.setCurentPage(0);
		info.setRead(false);
		info.setReadBook(librarianServise.findBookById(createBook()));
		info.setRidingPerson(peopleService
				.findPersonByName(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null));
		info.setEndReading(null);
		return info;
	}

	@Test
	void testSetReadingWhatBook() throws Exception {
		usersService.setReadingWhatBook(createBook());
		InformationBookPerson info = createInfo();

		Mockito.verify(informationsBookPersonRepository, Mockito.times(1)).save(info);

	}

}
