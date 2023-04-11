package ru.kovalev.homelibraryboot.models;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_name")
	@NotEmpty(message = "Имя не должно быть пустым")
	@Size(min = 8, max = 100, message = "Имя должно быть от 8 до 100 символов")
	private String userName;

	@Column(name = "password")
	@NotEmpty(message = "Пароль не должен быть пустым")
	@Size(min = 8, max = 100, message = "Пароль должен быть от 8 до 25 символов")
	private String password;

	@Column(name = "role")
	@NotEmpty(message = "Роль не должена быть пустым")
	private String role;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "createdWho")
	private List<Book> createdBooks;

	@OneToMany(mappedBy = "ridingPerson")
	private List<InformationBookPerson> informationPersons;

	public Person() {
	}

	public Person(
			@NotEmpty(message = "Имя не должно быть пустым") @Size(min = 8, max = 100, message = "Имя должно быть от 8 до 100 символов") String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, password, role, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(createdAt, other.createdAt) && id == other.id && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role
				+ ", createdAt=" + createdAt + "]";
	}

}
