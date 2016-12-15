package com.arturnowicki.flights.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.HibernateUtils;

public class AirportService {

	public List<Airport> getAirportsList() {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Airport> airports = session.createCriteria(Airport.class).list();
		Hibernate.initialize(airports);
		session.getTransaction().commit();
		return airports;
	}
	
	public Optional<Airport> getAirportById(int id) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Airport airport = (Airport) session.get(Airport.class, id);
		Hibernate.initialize(airport);
		session.getTransaction().commit();
		return Optional.ofNullable(airport);
	}
}
