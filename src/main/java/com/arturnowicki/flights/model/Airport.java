package com.arturnowicki.flights.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Airport", uniqueConstraints = { @UniqueConstraint(columnNames = { "airportCity" }) })
public class Airport implements Comparable<Airport> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "airportId", nullable = false, unique = true, length = 11)
	private int airportId;

	private String airportCity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departureCity")
	private List<Flight> departingFlights = new ArrayList<Flight>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "arrivalCity")
	private List<Flight> arrivingFlights = new ArrayList<Flight>();

	public Airport() {
	}

	public Airport(String airportCity) {
		this.airportCity = airportCity;
	}

	public int getAirportId() {
		return airportId;
	}

	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}

	public String getAirportCity() {
		return airportCity;
	}

	public void setAirportCity(String airportCity) {
		this.airportCity = airportCity;
	}
	
	public List<Flight> getDepartingFlights() {
		return departingFlights;
	}

	public void setDepartingFlights(List<Flight> flights) {
		this.departingFlights = flights;
	}

	public List<Flight> getArrivingFlights() {
		return arrivingFlights;
	}

	public void setArrivingFlights(List<Flight> arrivingFlights) {
		this.arrivingFlights = arrivingFlights;
	}

	@Override
	public String toString() {
		return "Airport [airportId=" + airportId + ", airportCity=" + airportCity + "]";
	}

	@Override
	public int compareTo(Airport a) {
		int condition = airportCity.compareToIgnoreCase(a.getAirportCity());
		if (condition == 0) {
			if (airportId == a.getAirportId()) {
				return 0;
			} else {
				return airportId - a.getAirportId();
			}
		} else {
			return condition;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airportCity == null) ? 0 : airportCity.hashCode());
		result = prime * result + airportId;
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
		Airport other = (Airport) obj;
		if (airportCity == null) {
			if (other.airportCity != null)
				return false;
		} else if (!airportCity.equals(other.airportCity))
			return false;
		if (airportId != other.airportId)
			return false;
		return true;
	}

}
