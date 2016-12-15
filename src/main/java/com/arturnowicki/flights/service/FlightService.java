package com.arturnowicki.flights.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.Flight;
import com.arturnowicki.flights.model.HibernateUtils;
import com.arturnowicki.flights.model.exceptions.WrongAirportException;

public class FlightService {
	
	public List<Flight> getFlightsFromAirport(int airportId) throws WrongAirportException {
		AirportService airportDAO = new AirportService();
		Optional<Airport> maybeAirport = airportDAO.getAirportById(airportId);
		if(maybeAirport.isPresent()) {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
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

	public List<Flight> getFlightsToAirport(int airportId) throws WrongAirportException {
		AirportService airportDAO = new AirportService();
		Optional<Airport> maybeAirport = airportDAO.getAirportById(airportId);
		if(maybeAirport.isPresent()) {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
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


}
