package com.arturnowicki.filghts.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.model.AirportDAO;
import com.arturnowicki.flights.model.HibernateUtils;

public class AirportDAOTest {

	private List<Airport> airportsForComparison;

	@Before
	public void initializeDB() {
		airportsForComparison = new ArrayList<>();
		Airport airport = new Airport("Warsaw");
		airport.setAirportId(1);
		airportsForComparison.add(airport);

		airport = new Airport("London");
		airport.setAirportId(2);
		airportsForComparison.add(airport);

		airport = new Airport("Frankfurt");
		airport.setAirportId(3);
		airportsForComparison.add(airport);

		airport = new Airport("Vienna");
		airport.setAirportId(4);
		airportsForComparison.add(airport);

		airport = new Airport("Paris");
		airport.setAirportId(5);
		airportsForComparison.add(airport);

		airport = new Airport("Berlin");
		airport.setAirportId(6);
		airportsForComparison.add(airport);
	}

	@Test
	public void testGetAirportByIdShouldReturnExistingAirport() {
		AirportDAO airportDAO = new AirportDAO();
		Optional<Airport> airport = airportDAO.getAirportById(6);
		assertTrue(airport.isPresent());
		assertEquals("Berlin", airport.get().getAirportCity());
	}

	@Test
	public void testGetAirportByIdShouldReturnNullForNoId() {
		AirportDAO airportDAO = new AirportDAO();
		Optional<Airport> airport = airportDAO.getAirportById(9);
		assertFalse(airport.isPresent());
	}

	@Test
	public void testShouldReturnAllAirports() {
		AirportDAO airportDAO = new AirportDAO();
		List<Airport> airports = airportDAO.getAirportsList();
		assertEquals(airportsForComparison.size(), airports.size());
		assertTrue(airports.containsAll(airportsForComparison));
		assertTrue(airportsForComparison.containsAll(airports));
	}

}
