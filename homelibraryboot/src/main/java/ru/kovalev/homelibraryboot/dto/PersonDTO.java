package ru.kovalev.homelibraryboot.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import ru.kovalev.homelibraryboot.models.Book;
import ru.kovalev.homelibraryboot.models.InformationBookPerson;

public class PersonDTO {

	private int id;

	@NotEmpty(message = "Имя не должно быть пустым")
	@Size(min = 8, max = 100, message = "Имя должно быть от 8 до 100 символов")
	private String userName;

	@NotEmpty(message = "Пароль не должен быть пустым")
	@Size(min = 8, max = 25, message = "Пароль должен быть от 8 до 25 символов")
	private String password;

	private String role;

	private LocalDateTime createdAt;

	private List<Book> createdBooks;

	private List<InformationBookPerson> informationPersons;

	public String getUserName() {
		return userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Book> getCreatedBooks() {
		return createdBooks;
	}

	public void setCreatedBooks(List<Book> createdBooks) {
		this.createdBooks = createdBooks;
	}

	public List<InformationBookPerson> getInformationPersons() {
		return informationPersons;
	}

	public void setInformationPersons(List<InformationBookPerson> informationPersons) {
		this.informationPersons = informationPersons;
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
