package com.arturnowicki.flights.model.flight;

import java.util.Optional;
import java.util.Set;

import org.hibernate.Session;

import com.arturnowicki.flights.model.airport.Airport;

public class FlightDAO {
	
	public Set<Flight> getFlightsFromAirport(Session session, int airportId) {
		session.beginTransaction();
		Airport airport = (Airport) session.get(Airport.class, airportId);
		Set<Flight> flights = airport.getDepartingFlights();
		flights.forEach(System.out :: println);
		session.getTransaction().commit();
		return flights;
	}

	public Set<Flight> getFlightsToAirport(Session session, int airportId) {
		session.beginTransaction();
		Airport airport = (Airport) session.get(Airport.class, airportId);
		Set<Flight> flights = airport.getArrivingFlights();
		flights.forEach(System.out :: println);
		session.getTransaction().commit();
		return flights;
	}

	public Optional<Flight> getFlightById(Session session, int id) {
		session.beginTransaction();
		Flight flight = (Flight) session.get(Flight.class, id);
		session.getTransaction().commit();
		return Optional.ofNullable(flight);
	}

}
