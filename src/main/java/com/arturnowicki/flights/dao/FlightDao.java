package com.arturnowicki.flights.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.Flight;
import com.arturnowicki.flights.model.HibernateUtils;
import com.arturnowicki.flights.model.exceptions.WrongAirportException;

public class FlightDao {


	public Flight addFlight(Flight flight) {
		Session session = startTransaction();
		try {
			session.save(flight);
			Hibernate.initialize(flight);
			session.getTransaction().commit();
			return flight;
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	public void updateFlight(Flight flight) {
		Session session = startTransaction();
		try {
			session.update(flight);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public void deleteFlight(Flight flight) {
		Session session = startTransaction();
		try {
			session.delete(flight);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	public List<Flight> getFlightsList() {
		Session session = startTransaction();
		List<Flight> flight = session.createCriteria(Flight.class).list();
		Hibernate.initialize(flight);
		session.getTransaction().commit();
		return flight;
	}

	public Optional<Flight> getFlightById(int id) {
		Session session = startTransaction();
		Flight flight = (Flight) session.get(Flight.class, id);
		session.getTransaction().commit();
		Hibernate.initialize(flight);
		return Optional.ofNullable(flight);
	}
	
	private static Session startTransaction() {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}
}
