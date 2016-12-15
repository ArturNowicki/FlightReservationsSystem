package com.arturnowicki.flights.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.Flight;
import com.arturnowicki.flights.model.HibernateUtils;

public class FlightService {
	
	public List<Flight> getFlightsFromAirport(int airportId) {
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
		}
		return null;
	}

	public List<Flight> getFlightsToAirport(int airportId) {
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
		}
		return null;
	}


}
