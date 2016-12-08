package com.arturnowicki.filghts.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AirportCompareToTest.class, FlightDAOTest.class, AirportDAOTest.class })
public class AllTests {}
