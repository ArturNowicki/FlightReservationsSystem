package com.arturnowicki.filghts.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;

import com.arturnowicki.flights.dao.AirportDao;
import com.arturnowicki.flights.model.Airport;
import com.arturnowicki.flights.service.AirportService;

public class AirportDaoTest {

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
	public void testAddAndDeleteAirportWhenAllOK() {
		int expectedSize = 6;
		AirportDao airportDao = new AirportDao();
		List<Airport> airportsList = airportDao.getAll(Airport.class);

		assertEquals(expectedSize, airportsList.size());

		Airport newAirport = new Airport("New York");
		Airport addedAirport = null;
		try {
			addedAirport = airportDao.add(newAirport);
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
		}
		airportsList = airportDao.getAll(Airport.class);

		assertEquals(expectedSize + 1, airportsList.size());
		assertTrue(airportsList.contains(addedAirport));

		airportDao.delete(addedAirport);

		airportsList = airportDao.getAll(Airport.class);

		assertEquals(expectedSize, airportsList.size());
		assertFalse(airportsList.contains(addedAirport));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAirportWhenAlreadyExists() {
		AirportDao airportDao = new AirportDao();
		Airport newAirport = new Airport("Warsaw");
		airportDao.add(newAirport);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testDeleteAirportWhenAirportUsed() {
		AirportDao airportDao = new AirportDao();
		airportDao.delete(airportsForComparison.get(1));
	}

	@Test
	public void testShouldReturnAllAirports() {
		AirportService airportDAO = new AirportService();
		List<Airport> airports = airportDAO.getAirportsList();
		assertEquals(airportsForComparison.size(), airports.size());
		assertTrue(airports.containsAll(airportsForComparison));
		assertTrue(airportsForComparison.containsAll(airports));
	}

	@Test
	public void testGetAirportByIdShouldReturnExistingAirport() {
		AirportService airportDAO = new AirportService();
		Optional<Airport> airport = airportDAO.getAirportById(6);
		assertTrue(airport.isPresent());
		assertEquals("Berlin", airport.get().getAirportCity());
	}

	@Test
	public void testGetAirportByIdShouldReturnNullForNoId() {
		AirportService airportDAO = new AirportService();
		Optional<Airport> airport = airportDAO.getAirportById(9);
		assertFalse(airport.isPresent());
	}

	@Test
	public void testUpdateAirportWhenAllOK() {
		int id = 1;
		AirportDao airportDao = new AirportDao();
		Airport airport = new Airport("New York");
		airport.setAirportId(id);
		airportDao.update(airport);
		Optional<Airport> maybeAirport = airportDao.getById(id);
		assertTrue(maybeAirport.isPresent());
		assertEquals(maybeAirport.get(), airport);
		airport.setAirportCity("Warsaw");
		airportDao.update(airport);
	}
}
