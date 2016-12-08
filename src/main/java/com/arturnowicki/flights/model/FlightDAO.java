package com.arturnowicki.flights.model;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.arturnowicki.flights.model.exceptions.WrongAirportException;

public class FlightDAO {
	
	public List<Flight> getFlightsFromAirport(SessionFactory sessionFactory, int airportId) throws WrongAirportException {
		AirportDAO airportDAO = new AirportDAO();
		Optional<Airport> maybeAirport = airportDAO.getAirportById(sessionFactory, airportId);
		if(maybeAirport.isPresent()) {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Airport airport = maybeAirport.get();
			session.update(airport);
			List<Flight> flights = airport.getDepartingFlights();
			Hibernate.initialize(flights);
			session.getTransaction().commit();
			return flights;
		} else {
			throw new WrongAirportException(String.valueOf(airportId));
		}
	}

	public List<Flight> getFlightsToAirport(SessionFactory sessionFactory, int airportId) throws WrongAirportException {
		AirportDAO airportDAO = new AirportDAO();
		Optional<Airport> maybeAirport = airportDAO.getAirportById(sessionFactory, airportId);
		if(maybeAirport.isPresent()) {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Airport airport = maybeAirport.get();
			session.update(airport);
			List<Flight> flights = airport.getArrivingFlights();
			Hibernate.initialize(flights);
			session.getTransaction().commit();
			return flights;
		} else {
			throw new WrongAirportException(String.valueOf(airportId));
		}
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
