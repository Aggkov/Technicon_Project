package com.europeandynamics.exceptions;

import com.europeandynamics.model.enums.HttpStatus;

public class InvalidEmailException extends RuntimeException {

	private HttpStatus httpStatus;
	public InvalidEmailException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
