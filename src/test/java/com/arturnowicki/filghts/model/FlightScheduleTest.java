package com.arturnowicki.filghts.model;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.Flight;
import com.arturnowicki.flights.model.FlightSchedule;

public class FlightScheduleTest {

	private int flightId = 1;
	private Flight flight;
	private FlightSchedule schedule;
	private String day = "MONDAY";
	private int scheduleId = 1;

	@Before
	public void createFlight() {
		Airport departureCity = new Airport("Warsaw");
		;
		Airport arrivalCity = new Airport("London");
		Time departureTime = Time.valueOf(LocalTime.of(11, 30, 00));
		Time arrivalTime = Time.valueOf(LocalTime.of(12, 20, 00));
		flight = new Flight(departureTime, departureCity, arrivalTime, arrivalCity);
		flight.setFlightId(flightId);
		schedule = new FlightSchedule(day, flight);
		schedule.setScheduleId(scheduleId);
	}

	@Test
	public void testScheduleConstructor() {
		assertEquals(scheduleId, schedule.getScheduleId());
		assertEquals(day, schedule.getDayOfWeek());
		assertEquals(flight, schedule.getFlight());
	}

	@Test
	public void testGetSetScheduleId() {
		int expectedId = 3;
		schedule.setScheduleId(expectedId);
		assertEquals(expectedId, schedule.getScheduleId());
	}

	@Test
	public void testGetSetDayOfWeek() {
		String expectedDay = "WEDNESDAY";
		schedule.setDayOfWeek(expectedDay);
		assertEquals(expectedDay, schedule.getDayOfWeek());
	}

	@Test
	public void testGetSetFlight() {
		Airport expectedDepartureCity = new Airport("Berlin");
		Airport expectedArrivalCity = new Airport("Vienna");
		Time expectedDepartureTime = Time.valueOf(LocalTime.of(11, 30, 00));
		Time expectedArrivalTime = Time.valueOf(LocalTime.of(12, 20, 00));
		int expectedFlightId = 5;
		Flight expectedFlight = new Flight(expectedDepartureTime, expectedDepartureCity, expectedArrivalTime, expectedArrivalCity);
		flight.setFlightId(expectedFlightId);
		
		schedule.setFlight(expectedFlight);
		assertEquals(expectedFlight, schedule.getFlight());
	}
}
