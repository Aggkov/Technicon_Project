package com.europeandynamics.exceptions;

public class InvalidEmailException extends RuntimeException {

	public InvalidEmailException(String message) {
		super(message);
	}

}
