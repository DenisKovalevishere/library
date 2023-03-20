package ru.kovalev.homelibraryboot.dto;

import java.time.LocalDateTime;


public class InformationBookPersonDTO {

	private LocalDateTime startReading;

	private LocalDateTime endReading;

	private Boolean read;
	
	private Integer curentPage;

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

	public Boolean getRead() {
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
