package com.arturnowicki.flights.model;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FlightScheduleDAO {

	public List<FlightSchedule> getScheduleFromDay(SessionFactory sessionFactory, DayOfWeek day) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
//		List<FlightSchedule> flightSchedule = session.createCriteria(FlightSchedule.class).list();
		List<FlightSchedule> flightSchedule = session.createFilter(FlightSchedule.class, "where).list();
		Hibernate.initialize(flightSchedule);
		session.getTransaction().commit();
		return flightSchedule;
	}
}
