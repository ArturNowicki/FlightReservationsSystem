package com.arturnowicki.filghts.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Test;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.HibernateUtils;

public class AirportTest {

	@Test
	public void testEqualsWhenEqual() {
		Airport expectedAirport = new Airport();
		expectedAirport.setAirportId(2);
		expectedAirport.setAirportCity("London");
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Airport airportUnderTest = (Airport) session.get(Airport.class, 2);
		Hibernate.initialize(airportUnderTest);
		session.getTransaction().commit();
		assertTrue(expectedAirport.equals(airportUnderTest));
	}
	
	@Test
	public void testEqualsWhenNotEqual() {
		Airport expectedAirport = new Airport();
		expectedAirport.setAirportId(2);
		expectedAirport.setAirportCity("Warsaw");
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Airport airportUnderTest = (Airport) session.get(Airport.class, 2);
		Hibernate.initialize(airportUnderTest);
		session.getTransaction().commit();
		assertFalse(expectedAirport.equals(airportUnderTest));
	}
	
	
	// Tests compare to
	@Test
	public void shouldReturnZeroIfEqual() {
		Airport a1 = new Airport("Warsaw");
		a1.setAirportId(2);
		Airport a2 = new Airport("Warsaw");
		a2.setAirportId(2);
		assertEquals(0, a1.compareTo(a2));
	}

	@Test
	public void shouldReturnPositiveIfSameNameGreaterId() {
		Airport a1 = new Airport("Warsaw");
		a1.setAirportId(2);
		Airport a2 = new Airport("Warsaw");
		a2.setAirportId(1);
		assertTrue(a1.compareTo(a2) > 0);
	}

	@Test
	public void shouldReturnPositiveIfSameNameSmallerId() {
		Airport a1 = new Airport("Warsaw");
		a1.setAirportId(2);
		Airport a2 = new Airport("Warsaw");
		a2.setAirportId(3);
		assertTrue(a1.compareTo(a2) < 0);
	}

	@Test
	public void shouldReturnPositiveIfGreaterName() {
		Airport a1 = new Airport("Warsaw");
		a1.setAirportId(2);
		Airport a2 = new Airport("Berlin");
		a2.setAirportId(7);
		assertTrue(a1.compareTo(a2) > 0);
	}

	@Test
	public void shouldReturnNegativeIfSmallerName() {
		Airport a1 = new Airport("Berlin");
		a1.setAirportId(2);
		Airport a2 = new Airport("Warsaw");
		a2.setAirportId(1);
		assertTrue(a1.compareTo(a2) < 0);
	}

	@Test
	public void shouldReturnPositiveIfGreaterNameSameId() {
		Airport a1 = new Airport("Warsaw");
		a1.setAirportId(2);
		Airport a2 = new Airport("Warsaw");
		a2.setAirportId(2);
		assertEquals(0, a1.compareTo(a2));
	}

	@Test
	public void shouldReturnNegativeIfSmallerNameSameId() {
		Airport a1 = new Airport("Warsaw");
		a1.setAirportId(2);
		Airport a2 = new Airport("Warsaw");
		a2.setAirportId(2);
		assertEquals(0, a1.compareTo(a2));
	}

}
