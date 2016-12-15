package com.arturnowicki.filghts.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.arturnowicki.filghts.service.AirportServiceTest;
import com.arturnowicki.filghts.service.FlightServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ AirportServiceTest.class, FlightServiceTest.class })
public class AllServiceTests {}
