package ru.kovalev.homelibraryboot.dto;

import java.time.LocalDateTime;

import ru.kovalev.homelibraryboot.models.Book;
import ru.kovalev.homelibraryboot.models.Person;


public class InformationBookPersonDTO {

	private int id;
	
	private LocalDateTime startReading;

	private LocalDateTime endReading;

	private Boolean read;
	
	private Integer curentPage;
	
	private Person ridingPerson;
	
	private Book readBook;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getRidingPerson() {
		return ridingPerson;
	}

	public void setRidingPerson(Person ridingPerson) {
		this.ridingPerson = ridingPerson;
	}

	public Book getReadBook() {
		return readBook;
	}

	public void setReadBook(Book readBook) {
		this.readBook = readBook;
	}

	public LocalDateTime getStartReading() {
		return startReading;
	}

	public void setStartReading(LocalDateTime startReading) {
		this.startReading = startReading;
	}

	public LocalDateTime getEndReading() {
		return endReading;
	}

	public void setEndReading(LocalDateTime endReading) {
		this.endReading = endReading;
	}

	public Boolean isRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Integer getCurentPage() {
		return curentPage;
	}

	public void setCurentPage(Integer curentPage) {
		this.curentPage = curentPage;
	}
	
	
}
