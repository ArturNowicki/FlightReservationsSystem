package com.arturnowicki.flights.service;

import java.time.DayOfWeek;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.arturnowicki.flights.model.FlightSchedule;

public class FlightScheduleService {

	public List<FlightSchedule> getScheduleFromDay(SessionFactory sessionFactory, DayOfWeek day) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<FlightSchedule> flightSchedule = session.createCriteria(FlightSchedule.class).add(Restrictions.eq("dayOfWeek", String.valueOf(day))).list();
//		List<FlightSchedule> flightSchedule = session.createFilter(FlightSchedule.class, "where).list();
		Hibernate.initialize(flightSchedule);
		session.getTransaction().commit();
		return flightSchedule;
	}
}
