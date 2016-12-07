package com.arturnowicki.flights.model.flight;

import java.util.Optional;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.arturnowicki.flights.model.airport.Airport;

public class FlightDAO {
	
	public Set<Flight> getFlightsFromAirport(SessionFactory sessionFactory, int airportId) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Airport airport = (Airport) session.get(Airport.class, airportId);
		Set<Flight> flights = airport.getDepartingFlights();
		Hibernate.initialize(flights);
		session.getTransaction().commit();
		return flights;
	}

	public Set<Flight> getFlightsToAirport(SessionFactory sessionFactory, int airportId) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Airport airport = (Airport) session.get(Airport.class, airportId);
		Set<Flight> flights = airport.getArrivingFlights();
		Hibernate.initialize(flights);
		session.getTransaction().commit();
		return flights;
	}

	public Optional<Flight> getFlightById(SessionFactory sessionFactory, int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Flight flight = (Flight) session.get(Flight.class, id);
		session.getTransaction().commit();
		Hibernate.initialize(flight);
		return Optional.ofNullable(flight);
	}

}
