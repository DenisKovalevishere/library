package ru.kovalev.homelibraryboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomelibrarybootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomelibrarybootApplication.class, args);
	}
		@Bean
		ModelMapper modelMapper() {
			return new ModelMapper();
	}
	
}
