package ru.kovalev.homelibraryboot.services;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ru.kovalev.homelibraryboot.models.Book;
import ru.kovalev.homelibraryboot.repositories.BooksRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser(username = "testnamelibrarian", password = "2222222222")
@TestPropertySource("/application-test.properties")
class LibrarianServiseTest {

	@Autowired
	private LibrarianServise librarianService;
	
	@MockBean
	private BooksRepository booksRepository;
	

	
	@Test
	void testSavePerson() {
		Book book = new Book("TestTitle", "TestAuthor", "TestLanguage", "TestDescription");
		librarianService.saveBook(book);
		
		Assert.assertTrue(CoreMatchers.is(book.getTitle()).matches("TestTitle"));
		Assert.assertNotNull(book.getCreatedAt());
		Assert.assertTrue(CoreMatchers.is(book.getAuthor()).matches("TestAuthor"));
		Assert.assertTrue(CoreMatchers.is(book.getLanguage()).matches("TestLanguage"));
		Assert.assertTrue(CoreMatchers.is(book.getDescription()).matches("TestDescription"));
		
		Mockito.verify(booksRepository, Mockito.times(1)).save(book);

	}

}
