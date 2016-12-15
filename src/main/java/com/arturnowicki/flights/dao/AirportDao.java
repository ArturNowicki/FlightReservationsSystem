package com.arturnowicki.flights.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.HibernateUtils;

public class AirportDao {

	public Airport addAirport(Airport airport) {
		Session session = startTransaction();
		try {
			session.save(airport);
			Hibernate.initialize(airport);
			session.getTransaction().commit();
			return airport;
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	public void updateAirport(Airport airport) {
		Session session = startTransaction();
		try {
			session.update(airport);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public void deleteAirport(Airport airport) {
		Session session = startTransaction();
		try {
			session.delete(airport);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	public List<Airport> getAirportsList() {
		Session session = startTransaction();
		List<Airport> airports = session.createCriteria(Airport.class).list();
		Hibernate.initialize(airports);
		session.getTransaction().commit();
		return airports;
	}

	public Optional<Airport> getAirportById(int id) {
		Session session = startTransaction();
		Airport airport = (Airport) session.get(Airport.class, id);
		Hibernate.initialize(airport);
		session.getTransaction().commit();
		return Optional.ofNullable(airport);
	}

	private static Session startTransaction() {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}
}
