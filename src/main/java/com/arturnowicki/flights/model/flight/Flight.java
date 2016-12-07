package com.arturnowicki.flights.model.flight;

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

import com.arturnowicki.flights.model.airport.Airport;

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

	public void setDepartureCity(Airport departureCity) {
		this.departureCity = departureCity;
	}

	public Airport getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(Airport arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ ", departureCity=" + departureCity + ", arrivalCity=" + arrivalCity + "]";
	}

	
}
