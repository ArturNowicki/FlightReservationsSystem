package com.arturnowicki.flights.model.flight;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	private int departureCity;
	private int  arrivalCity;

	public Flight() {
	}

	public Flight(Time departureTime, int departureCity, Time arrivalTime, int arrivalCity) {
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

	public int getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(int departureCity) {
		this.departureCity = departureCity;
	}

	public int getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(int arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	@Override
	public String toString() {
		return "Flight number " + flightId + ", departure: " + departureTime + " " + departureCity + ", arrival: "
				+ arrivalTime + " " + arrivalCity;
	}

}
