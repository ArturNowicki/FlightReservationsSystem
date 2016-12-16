package com.arturnowicki.flights.model;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode());
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + scheduleId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightSchedule other = (FlightSchedule) obj;
		if (dayOfWeek == null) {
			if (other.dayOfWeek != null)
				return false;
		} else if (!dayOfWeek.equals(other.dayOfWeek))
			return false;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (scheduleId != other.scheduleId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FlightSchedule [scheduleId=" + scheduleId + ", dayOfWeek=" + dayOfWeek + ", flight=" + flight + "]";
	}
	
}
