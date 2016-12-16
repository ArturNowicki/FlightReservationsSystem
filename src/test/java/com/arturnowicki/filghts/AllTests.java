package com.arturnowicki.filghts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.arturnowicki.filghts.dao.AllDaoTests;
import com.arturnowicki.filghts.model.AllModelTests;
import com.arturnowicki.filghts.service.AllServiceTests;

@RunWith(Suite.class)
@SuiteClasses({ AllModelTests.class, AllDaoTests.class, AllServiceTests.class })
public class AllTests {

}