package ru.kovalev.homelibraryboot.models;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "information_book_person")
public class InformationBookPerson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "start_reading")
	private LocalDateTime startReading;
	
	@Column(name = "end_reading")
	private LocalDateTime endReading;
	
	@Column(name = "read")
	private Boolean read;
	
	@Column(name = "curent_page")
	private Integer curentPage;
	
	@ManyToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person ridingPerson;
	
	@ManyToOne
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book readBook;
	
	public InformationBookPerson() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(curentPage, id, read, readBook, ridingPerson);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformationBookPerson other = (InformationBookPerson) obj;
		return Objects.equals(curentPage, other.curentPage) && id == other.id && Objects.equals(read, other.read)
				&& Objects.equals(readBook, other.readBook) && Objects.equals(ridingPerson, other.ridingPerson);
	}

	@Override
	public String toString() {
		return "InformationBookPerson [id=" + id + ", startReading=" + startReading + ", endReading=" + endReading
				+ ", read=" + read + ", curentPage=" + curentPage + ", ridingPerson=" + ridingPerson + ", readBook="
				+ readBook + "]";
	}
	
	
	
	
}
