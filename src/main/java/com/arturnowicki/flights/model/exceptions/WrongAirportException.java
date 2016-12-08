package com.arturnowicki.flights.model.exceptions;

public class WrongAirportException extends Exception {

	public WrongAirportException(String s) {
		super("Wrong airport id: " + s);
	}

}
