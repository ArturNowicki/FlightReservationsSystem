package com.arturnowicki.filghts.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.Flight;
import com.arturnowicki.flights.model.FlightDAO;
import com.arturnowicki.flights.model.HibernateUtils;
import com.arturnowicki.flights.model.exceptions.WrongAirportException;

public class FlightDAOTest {

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initializeSessionFactory() {
		sessionFactory = HibernateUtils.getSessionFactory();
	}

	@AfterClass
	public static void deinitSession() {
		sessionFactory.close();
	}

	@Test(expected = WrongAirportException.class)
	public void testGetFlightsFromAirportInvalidAirportId() throws WrongAirportException {

		int departureId = -1;
		FlightDAO flightDAO = new FlightDAO();
		List<Flight> flights = flightDAO.getFlightsFromAirport(sessionFactory, departureId);
	}

	@Test(expected = WrongAirportException.class)
	public void testGetFlightsToAirportInvalidAirportId() throws WrongAirportException {

		int arrivalId = -1;
		FlightDAO flightDAO = new FlightDAO();
		List<Flight> flights = flightDAO.getFlightsToAirport(sessionFactory, arrivalId);
	}

	@Test
	public void testGetFlightsFromAirportValidAirportId() {

		int expectedDepartureId = 1;
		String expectedDepartureName = "Warsaw";
		Airport expectedDepartureCity = new Airport(expectedDepartureName);
		expectedDepartureCity.setAirportId(expectedDepartureId);
		Time expectedDepartureTime = Time.valueOf(LocalTime.parse("11:30:00"));

		int expectedArrivalId = 2;
		String expectedArrivalName = "London";
		Airport expectedArrivalCity = new Airport(expectedArrivalName);
		expectedArrivalCity.setAirportId(expectedArrivalId);
		Time expectedArrivalTime = Time.valueOf(LocalTime.parse("12:20:00"));

		int expectedFlightId = 1;
		Flight expectedFlight = new Flight(expectedDepartureTime, expectedDepartureCity, expectedArrivalTime,
				expectedArrivalCity);
		expectedFlight.setFlightId(expectedFlightId);

		FlightDAO flightDAO = new FlightDAO();

		try {
			List<Flight> flights;
			flights = flightDAO.getFlightsFromAirport(sessionFactory, expectedDepartureId);
			assertEquals(2, flights.size());
			assertTrue(flights.contains(expectedFlight));
		} catch (WrongAirportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testGetFlightsToAirportValidAirportId() {

		int expectedDepartureId = 1;
		String expectedDepartureName = "Warsaw";
		Airport expectedDepartureCity = new Airport(expectedDepartureName);
		expectedDepartureCity.setAirportId(expectedDepartureId);
		Time expectedDepartureTime = Time.valueOf(LocalTime.parse("11:30:00"));

		int expectedArrivalId = 2;
		String expectedArrivalName = "London";
		Airport expectedArrivalCity = new Airport(expectedArrivalName);
		expectedArrivalCity.setAirportId(expectedArrivalId);
		Time expectedArrivalTime = Time.valueOf(LocalTime.parse("12:20:00"));

		int expectedFlightId = 1;
		Flight expectedFlight = new Flight(expectedDepartureTime, expectedDepartureCity, expectedArrivalTime,
				expectedArrivalCity);
		expectedFlight.setFlightId(expectedFlightId);

		FlightDAO flightDAO = new FlightDAO();

		try {
			List<Flight> flights;
			flights = flightDAO.getFlightsToAirport(sessionFactory, expectedArrivalId);
			assertEquals(2, flights.size());
			assertTrue(flights.contains(expectedFlight));
		} catch (WrongAirportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testReturnFlightByIdWhenInvalidId() {
		int flightId = 9;
		FlightDAO flightDAO = new FlightDAO();
		Optional<Flight> maybeFlifht = flightDAO.getFlightById(sessionFactory, flightId);
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
		Optional<Flight> maybeFlight = flightDAO.getFlightById(sessionFactory, flightId);
		assertTrue("isPresent", maybeFlight.isPresent());
		Flight flight = maybeFlight.get();
		assertTrue("isEqual", compareFlights(expectedFlight, flight));
	}

	private boolean compareFlights(Flight f1, Flight f2) {
		if (f1.getFlightId() != f2.getFlightId()) {
			return false;
		}
		if (!(f1.getDepartureCity().equals(f2.getDepartureCity()))
				|| !(f1.getArrivalCity().equals(f2.getArrivalCity()))) {
			return false;
		}
		if (!(f1.getDepartureTime().equals(f2.getDepartureTime()))
				|| !(f1.getArrivalTime().equals(f2.getArrivalTime()))) {
			return false;
		}
		return true;
	}

}
