package com.arturnowicki.filghts.model.flight;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.arturnowicki.flights.model.HibernateUtils;
import com.arturnowicki.flights.model.airport.Airport;
import com.arturnowicki.flights.model.flight.Flight;
import com.arturnowicki.flights.model.flight.FlightDAO;

public class FlightDAOTest {

	private static SessionFactory sessionFactory;
	private static Session session;

	@BeforeClass
	public static void initializeSessionFactory() {
		sessionFactory = HibernateUtils.getSessionFactory();
	}

	@Before
	public void initializeSession() {
		session = sessionFactory.getCurrentSession();
	}

	@AfterClass
	public static void deinitSession() {
		sessionFactory.close();
	}
	
	@Test
	public void testGetFlightsFromAirportValidAirportId() {
		Airport departureCity = new Airport("Warsaw");
		departureCity.setAirportId(1);
		FlightDAO flightDAO = new FlightDAO();
		Set<Flight> flights = flightDAO.getFlightsFromAirport(session, 1);
		flights.size();
	}

	@Test
	public void testGetFlightsFromAirportInvalidAirportId() {
		Airport departureCity = new Airport("Warsaw");
		departureCity.setAirportId(1);
		FlightDAO flightDAO = new FlightDAO();
		Set<Flight> flights = flightDAO.getFlightsFromAirport(session, 1);
		flights.forEach(System.out :: println);
	}

	@Test
	public void testReturnFlightByIdWhenInvalidId() {
		int flightId = 9;
		FlightDAO flightDAO = new FlightDAO();
		Optional<Flight> maybeFlifht = flightDAO.getFlightById(session, flightId);
		assertFalse(maybeFlifht.isPresent());
	}

	@Test
	public void testReturnFlightByIdWhenValidId() {
		int flightId = 1;
		Airport departureCity = new Airport("Warsaw");
		departureCity.setAirportId(1);
		Airport arrivalCity = new Airport("London");
		arrivalCity.setAirportId(2);
		Time expectedDepartureTime = Time.valueOf(LocalTime.parse("11:30:00"));
		Time expectedArrivalTime = Time.valueOf(LocalTime.parse("12:20:00"));
		Flight expectedFlight = new Flight(expectedDepartureTime, departureCity, expectedArrivalTime, arrivalCity);
		expectedFlight.setFlightId(1);
		FlightDAO flightDAO = new FlightDAO();
		Optional<Flight> maybeFlight = flightDAO.getFlightById(session, flightId);
		assertTrue("isPresent", maybeFlight.isPresent());
		Flight flight = maybeFlight.get();
		System.out.println(flight.getDepartureTime());
		System.out.println(flight.getDepartureCity());
		assertTrue("isEqual", compareFlights(expectedFlight, flight));
	}

	private boolean compareFlights(Flight f1, Flight f2) {
		if (f1.getFlightId() != f2.getFlightId()) {
			return false;
		}
		if (!(f1.getDepartureCity().equals(f2.getDepartureCity())) || !(f1.getArrivalCity().equals(f2.getArrivalCity()))) {
			return false;
		}
		if (!(f1.getDepartureTime().equals(f2.getDepartureTime())) || !(f1.getArrivalTime().equals(f2.getArrivalTime()))) {
			return false;
		}
		return true;
	}

}
