package ru.kovalev.homelibraryboot.models;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	@NotEmpty(message = "Название не должно быть пустым")
	@Size(min = 2, max = 100 , message = "Название должно быть от 2 до 100 символов")
	private String title;
	
	@Column(name = "author")
	@NotEmpty(message = "Автор не должен быть пустым")
	@Size(min = 2, max = 100, message = "Автор должен быть от 2 до 100 символов")
	private String author;
	
	@Column(name = "language")
	@NotEmpty(message = "Язык не должена быть пустым")
	private String language;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "created_who", referencedColumnName = "id")
	private Person createdWho;
	
	@OneToMany(mappedBy = "readBook")
	private List<InformationBookPerson> informationBooks;
	
	public Book() {}

	public Book(
			@NotEmpty(message = "Название не должно быть пустым") @Size(min = 2, max = 100, message = "Название должно быть от 2 до 100 символов") String title,
			@NotEmpty(message = "Автор не должен быть пустым") @Size(min = 2, max = 100, message = "Автор должен быть от 2 до 100 символов") String author,
			@NotEmpty(message = "Язык не должена быть пустым") String language, String description) {
		this.title = title;
		this.author = author;
		this.language = language;
		this.description=description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Person getCreatedWho() {
		return createdWho;
	}

	public void setCreatedWho(Person createdWho) {
		this.createdWho = createdWho;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<InformationBookPerson> getInformationBooks() {
		return informationBooks;
	}

	public void setInformationBooks(List<InformationBookPerson> informationBooks) {
		this.informationBooks = informationBooks;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, createdAt, id, language, title, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(createdAt, other.createdAt) && id == other.id
				&& Objects.equals(language, other.language) && Objects.equals(title, other.title) && Objects.equals(description, other.description);
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", language=" + language + ", createdAt=" + createdAt
				+ ", description=" + description + ", createdWho=" + createdWho + "]";
	}

	
	
	
}
