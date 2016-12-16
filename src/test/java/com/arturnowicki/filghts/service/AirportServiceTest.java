package com.arturnowicki.filghts.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.Flight;
import com.arturnowicki.flights.service.AirportService;

@SuppressWarnings("unused")
public class AirportServiceTest {

	@Test
	public void testGetFlightsFromAirportInvalidAirportId() {
		int depId = -1;
		String expectedMessage = "Invalid airport Id " + depId;
		AirportService service = new AirportService();

		try {
			List<Flight> dbFlights = service.getFlightsFromAirport(depId);
			fail();
		} catch (IllegalArgumentException e) {
			System.out.println(expectedMessage);
			System.out.println(e.getMessage());
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void testGetFlightsFromAirportNoFlights() {
		int depId = 7;
		AirportService service = new AirportService();
		List<Flight> dbFlights = service.getFlightsFromAirport(depId);
		assertTrue(dbFlights.isEmpty());
	}

	@Test
	public void testGetFlightsFromAirportAllOK() {
		AirportService service = new AirportService();
		List<Flight> flights = prepareDepartureList();
		List<Flight> dbFlights = service.getFlightsFromAirport(1);

		assertEquals(flights.size(), dbFlights.size());
		assertEquals(flights, dbFlights);
	}

	@Test
	public void testGetFlightsToAirportInvalidAirportId() {
		int arrId = -1;
		String expectedMessage = "Invalid airport Id " + arrId;
		AirportService service = new AirportService();

		try {
			List<Flight> dbFlights = service.getFlightsToAirport(arrId);
			fail();
		} catch (IllegalArgumentException e) {
			System.out.println(expectedMessage);
			System.out.println(e.getMessage());
			assertEquals(expectedMessage, e.getMessage());
		}
	}
	
	@Test
	public void testGetFlightsToAirportNoFlights() {
		int arrId = 7;
		AirportService service = new AirportService();
		List<Flight> dbFlights = service.getFlightsToAirport(arrId);
		assertTrue(dbFlights.isEmpty());
	}

	@Test
	public void testGetFlightsToAirportAllOK() {
		AirportService service = new AirportService();
		List<Flight> flights = prepareArrivalList();
		List<Flight> dbFlights = service.getFlightsToAirport(1);

		assertEquals(flights.size(), dbFlights.size());
		assertEquals(flights, dbFlights);
	}

	private List<Flight> prepareDepartureList() {
		int depId = 1;
		int arrId = 2;
		int flightId = 1;
		String depName = "Warsaw";
		String arrName = "London";
		Time depTime = Time.valueOf(LocalTime.parse("11:30"));
		Time arrTime = Time.valueOf(LocalTime.parse("12:20"));
		Flight flight1 = createFlight(flightId, depId, arrId, depName, arrName, depTime, arrTime);

		arrId = 5;
		flightId = 5;
		arrName = "Paris";
		depTime = Time.valueOf(LocalTime.parse("15:00"));
		arrTime = Time.valueOf(LocalTime.parse("17:05"));
		Flight flight2 = createFlight(flightId, depId, arrId, depName, arrName, depTime, arrTime);
		List<Flight> flights = new ArrayList<>();
		flights.add(flight1);
		flights.add(flight2);
		return flights;
	}

	private List<Flight> prepareArrivalList() {
		int depId = 2;
		int arrId = 1;
		int flightId = 2;
		String depName = "London";
		String arrName = "Warsaw";
		Time depTime = Time.valueOf(LocalTime.parse("15:15"));
		Time arrTime = Time.valueOf(LocalTime.parse("16:05"));
		Flight flight1 = createFlight(flightId, depId, arrId, depName, arrName, depTime, arrTime);

		depId = 5;
		flightId = 6;
		depName = "Paris";
		depTime = Time.valueOf(LocalTime.parse("19:10"));
		arrTime = Time.valueOf(LocalTime.parse("21:15"));
		Flight flight2 = createFlight(flightId, depId, arrId, depName, arrName, depTime, arrTime);
		List<Flight> flights = new ArrayList<>();
		flights.add(flight1);
		flights.add(flight2);
		return flights;
	}

	private Flight createFlight(int flightId, int depId, int arrId, String departureName, String arrivalName,
			Time depTime, Time arrTime) {
		Airport expectedDepartureCity = new Airport(departureName);
		expectedDepartureCity.setAirportId(depId);
		Airport arrivalCity = new Airport(arrivalName);
		arrivalCity.setAirportId(arrId);
		Flight flight = new Flight(depTime, expectedDepartureCity, arrTime, arrivalCity);
		flight.setFlightId(flightId);
		return flight;
	}
}
