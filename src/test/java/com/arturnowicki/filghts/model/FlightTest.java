package com.arturnowicki.filghts.model;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.Flight;

public class FlightTest {

	private Flight flight;
	private Airport departureCity = new Airport("Warsaw");;
	private Airport arrivalCity = new Airport("London");
	private Time departureTime = Time.valueOf(LocalTime.of(11, 30, 00));
	private Time arrivalTime = Time.valueOf(LocalTime.of(12, 20, 00));

	@Before
	public void createFlight() {
		flight = new Flight(departureTime, departureCity, arrivalTime, arrivalCity);
		flight.setFlightId(1);
	}

	@Test
	public void testSetGetFlightId() {
		flight.setFlightId(3);
		assertEquals(3, flight.getFlightId());
		flight.setFlightId(1);
	}

	@Test
	public void testSetGetDepartureTime() {
		assertEquals(departureTime, flight.getDepartureTime());
		Time expectedTime = Time.valueOf(LocalTime.of(14, 25));
		flight.setDepartureTime(expectedTime);
		assertEquals(expectedTime, flight.getDepartureTime());
	}

	@Test
	public void testGetArrivalTime() {
		assertEquals(arrivalTime, flight.getArrivalTime());
		Time expectedTime = Time.valueOf(LocalTime.of(14, 25));
		flight.setArrivalTime(expectedTime);
		assertEquals(expectedTime, flight.getArrivalTime());
	}

	@Test
	public void testGetDepartureCity() {
		assertEquals(departureCity, flight.getDepartureCity());
	}

	@Test
	public void testGetArrivalCity() {
		assertEquals(arrivalCity, flight.getArrivalCity());
	}

}
