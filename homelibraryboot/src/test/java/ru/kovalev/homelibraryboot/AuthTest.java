package ru.kovalev.homelibraryboot;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void contextLoads() throws Exception{
		this.mockMvc.perform(get("/auth/login"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Login")))
		.andExpect(content().string(containsString("Введите имя пользователя:")))
		.andExpect(content().string(containsString("Введите пароль:")));
	}
	
	@Test
	public void accessDeniedTest() throws Exception{
		this.mockMvc.perform(get("/people"))
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("http://localhost/auth/login"));
	}
	
	@Test
	public void log() throws Exception{
		this.mockMvc.perform(logout())
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/auth/login"));
	}
	
	@Test
	public void correctLoginTest() throws Exception{
		this.mockMvc.perform(formLogin("/process_login").user("testnameadmin").password("1"))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/library"));
	}
	
	@Test
	public void badCredentials() throws Exception{
		this.mockMvc.perform(post("/login").param("user", "testname12345"))
		.andDo(print())
		.andExpect(status().isForbidden());
	}
}
