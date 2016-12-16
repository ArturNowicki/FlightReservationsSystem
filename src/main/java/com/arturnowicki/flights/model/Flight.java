package com.arturnowicki.flights.model;

import java.sql.Time;

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
@Table(name = "Flight", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "departureTime", "departureCity", "arrivalTime", "arrivalCity" })})
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "flightId", nullable = false, unique = true, length = 11)
	private int flightId;

	private Time departureTime;
	private Time arrivalTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "departureCity")
	private Airport departureCity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "arrivalCity")
	private Airport arrivalCity;

	public Flight() {
	}

	public Flight(Time departureTime, Airport departureCity, Time arrivalTime, Airport arrivalCity) {
		this.departureTime = departureTime;
		this.departureCity = departureCity;
		this.arrivalTime = arrivalTime;
		this.arrivalCity = arrivalCity;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Airport getDepartureCity() {
		return departureCity;
	}

//	public void setDepartureCity(Airport departureCity) {
//		this.departureCity = departureCity;
//	}
//
	public Airport getArrivalCity() {
		return arrivalCity;
	}

//	public void setArrivalCity(Airport arrivalCity) {
//		this.arrivalCity = arrivalCity;
//	}
//
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalCity == null) ? 0 : arrivalCity.hashCode());
		result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result + ((departureCity == null) ? 0 : departureCity.hashCode());
		result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + flightId;
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
		Flight other = (Flight) obj;
		if (arrivalCity == null) {
			if (other.arrivalCity != null)
				return false;
		} else if (!arrivalCity.equals(other.arrivalCity))
			return false;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (departureCity == null) {
			if (other.departureCity != null)
				return false;
		} else if (!departureCity.equals(other.departureCity))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (flightId != other.flightId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ ", departureCity=" + departureCity + ", arrivalCity=" + arrivalCity + "]";
	}

	
}