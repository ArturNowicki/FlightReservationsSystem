package com.arturnowicki.filghts.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AirportEqualsTest.class, FlightTest.class, FlightScheduleTest.class })
public class AllModelTests {}