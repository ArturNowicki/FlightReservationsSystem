package com.arturnowicki.filghts;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.arturnowicki.filghts.model.AllModelTests;

public class AllTestsRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(AllModelTests.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}
}