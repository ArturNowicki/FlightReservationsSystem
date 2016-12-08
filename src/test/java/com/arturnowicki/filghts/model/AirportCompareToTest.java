package com.arturnowicki.filghts.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.arturnowicki.flights.model.Airport;

public class AirportCompareToTest {
	
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
		assertTrue(a1.compareTo(a2)>0);
	}

	@Test
	public void shouldReturnPositiveIfSameNameSmallerId() {
		Airport a1 = new Airport("Warsaw");
		a1.setAirportId(2);
		Airport a2 = new Airport("Warsaw");
		a2.setAirportId(3);
		assertTrue(a1.compareTo(a2)<0);
	}

	@Test
	public void shouldReturnPositiveIfGreaterName() {
		Airport a1 = new Airport("Warsaw");
		a1.setAirportId(2);
		Airport a2 = new Airport("Berlin");
		a2.setAirportId(7);
		assertTrue(a1.compareTo(a2)>0);
	}

	@Test
	public void shouldReturnNegativeIfSmallerName() {
		Airport a1 = new Airport("Berlin");
		a1.setAirportId(2);
		Airport a2 = new Airport("Warsaw");
		a2.setAirportId(1);
		assertTrue(a1.compareTo(a2)<0);
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
