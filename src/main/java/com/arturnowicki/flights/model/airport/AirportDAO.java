package com.arturnowicki.flights.model.airport;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;

public class AirportDAO {

	public List<Airport> getAirportsList(Session session) {
		session.beginTransaction();
		List<Airport> airports = session.createQuery("from Airport").list();
		session.getTransaction().commit();
		return airports;
	}
	
	public Optional<Airport> getAirportById(Session session, int id) {
		session.beginTransaction();
		Airport airport = (Airport) session.get(Airport.class, id);
		Hibernate.initialize(airport);
		session.getTransaction().commit();
		return Optional.ofNullable(airport);
	}
}
