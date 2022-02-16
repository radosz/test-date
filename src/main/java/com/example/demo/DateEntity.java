package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class DateEntity {

	@Id
	public long id;

	private Instant instantDate;

	private LocalDateTime localDateTime;

	public long getId() {
		return id;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Instant getInstantDate() {
		return instantDate;
	}

	public void setInstantDate(Instant instantDate) {
		this.instantDate = instantDate;
	}
}
