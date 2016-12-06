package com.arturnowicki.filghts.model.flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.arturnowicki.flights.model.HibernateUtils;
import com.arturnowicki.flights.model.flight.Flight;
import com.arturnowicki.flights.model.flight.FlightDAO;

public class FlightDAOTest {

	private static SessionFactory sessionFactory;
	private static Session session;
	private List<Flight> flightsForComparison;

	@BeforeClass
	public static void initializeSessionFactory() {
		sessionFactory = HibernateUtils.getSessionFactory();
	}

	@Before
	public void initializeDB() {
		session = sessionFactory.getCurrentSession();
	}

	@AfterClass
	public static void deinitSession() {
		sessionFactory.close();
	}

	@Test
	public void testReturnFlightByIdWhenIdValid() {
		int flightId = 1;
		FlightDAO flightDAO = new FlightDAO();
		Optional<Flight> maybeFlifht = flightDAO.getFlightById(session, flightId);
		assertTrue(maybeFlifht.isPresent());
		Flight flight = maybeFlifht.get();
		System.out.println(flight);
	}

}
