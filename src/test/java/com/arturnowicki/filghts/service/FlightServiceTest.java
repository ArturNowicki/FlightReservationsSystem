package com.arturnowicki.filghts.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.junit.Test;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.Flight;
import com.arturnowicki.flights.service.FlightService;

public class FlightServiceTest {

	@Test
	public void testGetFlightsFromAirportInvalidAirportId() {

		int departureId = -1;
		FlightService flightDAO = new FlightService();
		List<Flight> flights = flightDAO.getFlightsFromAirport(departureId);
	}

	@Test
	public void testGetFlightsToAirportInvalidAirportId() {

		int arrivalId = -1;
		FlightService flightDAO = new FlightService();
		List<Flight> flights = flightDAO.getFlightsToAirport(arrivalId);
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

		FlightService flightDAO = new FlightService();

			List<Flight> flights;
			flights = flightDAO.getFlightsFromAirport(expectedDepartureId);
			assertEquals(2, flights.size());
			assertTrue(flights.contains(expectedFlight));

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

		FlightService flightDAO = new FlightService();

			List<Flight> flights;
			flights = flightDAO.getFlightsToAirport(expectedArrivalId);
			assertEquals(2, flights.size());
			assertTrue(flights.contains(expectedFlight));

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
