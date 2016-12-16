package com.arturnowicki.flights.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.arturnowicki.flights.dao.AirportDao;
import com.arturnowicki.flights.dao.CommonDao;
import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.Flight;
import com.arturnowicki.flights.model.HibernateUtils;

public class AirportService {
	
	public List<Flight> getFlightsFromAirport(int airportId) throws IllegalArgumentException {
		CommonDao airportDAO = new AirportDao();
		Optional<Airport> maybeAirport = airportDAO.getById(Airport.class, airportId);
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
			throw new IllegalArgumentException("Invalid airport Id " + airportId);
		}
	}

	public List<Flight> getFlightsToAirport(int airportId) {
		CommonDao airportDAO = new AirportDao();
		Optional<Airport> maybeAirport = airportDAO.getById(Airport.class, airportId);
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
			throw new IllegalArgumentException("Invalid airport Id " + airportId);
		}
	}
}
