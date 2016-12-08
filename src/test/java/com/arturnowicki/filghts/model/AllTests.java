package com.arturnowicki.filghts.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AirportDAOTest.class, AirportCompareToTest.class, FlightDAOTest.class })
public class AllTests {}
