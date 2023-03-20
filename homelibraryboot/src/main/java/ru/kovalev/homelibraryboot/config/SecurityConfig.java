package ru.kovalev.homelibraryboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.kovalev.homelibraryboot.services.PersonsDetailService;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final PersonsDetailService personsDetailService;
	
	
	
	public SecurityConfig(PersonsDetailService personsDetailService) {
		this.personsDetailService = personsDetailService;
	}

	@Override
	protected void configure (HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(requests -> requests
                        .antMatchers("/people", "/people/new", "/people/{id}", "/people/{id}/edit").hasRole("ADMIN")
                        .antMatchers("/books/new", "/books/{id}/edit").hasRole("LIBRARIAN")//delite add
                        .antMatchers("/info", "/info/{id}", "/info/readed", "/info{id}/set_readed", "/info{id}/set_page", "/books/{id}/check").hasRole("USER")
                        .antMatchers("/auth/login", "/auth/registration", "/error")
                        .permitAll())
//			.anyRequest().hasAnyRole("LIBRARIAN", "USER") //Exception
//                        .anyRequest().hasAnyRole("ADMIN", "LIBRARIAN", "USER"))
                .formLogin(login -> login.loginPage("/auth/login")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/library", true)
                        .failureUrl("/auth/login?error"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(personsDetailService).passwordEncoder(getPasswordEncoder());
	}
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
