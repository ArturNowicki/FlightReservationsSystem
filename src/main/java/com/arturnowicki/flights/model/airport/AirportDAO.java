package com.arturnowicki.flights.model.airport;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AirportDAO {

	public List<Airport> getAirportsList(SessionFactory sessionFactory) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Airport> airports = session.createCriteria(Airport.class).list();
		Hibernate.initialize(airports);
		session.getTransaction().commit();
		return airports;
	}
	
	public Optional<Airport> getAirportById(SessionFactory sessionFactory, int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Airport airport = (Airport) session.get(Airport.class, id);
		Hibernate.initialize(airport);
		session.getTransaction().commit();
		return Optional.ofNullable(airport);
	}
}
