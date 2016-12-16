package com.arturnowicki.filghts.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.arturnowicki.filghts.service.AirportServiceTest;
import com.arturnowicki.filghts.service.FlightServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ AirportTest.class })
public class AllModelTests {}
