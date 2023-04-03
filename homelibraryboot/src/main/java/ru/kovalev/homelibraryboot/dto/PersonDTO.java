package ru.kovalev.homelibraryboot.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {


	@NotEmpty(message = "Имя не должно быть пустым")
	@Size(min = 8, max = 100 , message = "Имя должно быть от 8 до 100 символов")
	private String userName;

	@NotEmpty(message = "Пароль не должен быть пустым")
	@Size(min = 8, max = 25, message = "Пароль должен быть от 8 до 25 символов")
	private String password;
	
	
	private String role;

	private LocalDateTime createdAt;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	
}
