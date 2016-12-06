package com.arturnowicki.flights.model.flight;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;

public class FlightDAO {
	
	public List<Flight> getFlightsFromAirport(Session session, int airportId) {
		session.beginTransaction();
		List<Flight> flights = session.createQuery("from Flight").list();
		session.getTransaction().commit();
		return flights;
	}
	
	public Optional<Flight> getFlightById(Session session, int id) {
		session.beginTransaction();
		Flight flight = (Flight) session.get(Flight.class, id);
		Hibernate.initialize(flight);
		session.getTransaction().commit();
		return Optional.ofNullable(flight);
	}

}
