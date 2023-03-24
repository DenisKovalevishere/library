package ru.kovalev.homelibraryboot.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

public class BookDTO {



	@Size(min = 2, max = 100 , message = "Название должно быть от 2 до 100 символов")
	private String title;

	@Size(min = 2, max = 100, message = "Автор должен быть от 2 до 100 символов")
	private String author;

	private String language;
	
	private String description;

	private LocalDateTime createdAt;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
