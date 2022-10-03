package com.europeandynamics.exceptions;

import com.europeandynamics.model.enums.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {

	private HttpStatus httpStatus;

	public ResourceNotFoundException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
}
