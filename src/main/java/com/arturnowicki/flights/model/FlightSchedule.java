package com.arturnowicki.flights.model;

import java.time.DayOfWeek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "FlightSchedule", uniqueConstraints = {@UniqueConstraint(columnNames = {"idFlight", "dayOfWeek"})})
public class FlightSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "scheduleId", nullable = false, unique = true, length = 11)
	private int scheduleId;
	
	private String dayOfWeek;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idFlight")
	private Flight flight;

	public FlightSchedule() {}

	public FlightSchedule(String dayOfWeek, Flight flight) {
		this.dayOfWeek = dayOfWeek;
		this.flight = flight;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	
}
