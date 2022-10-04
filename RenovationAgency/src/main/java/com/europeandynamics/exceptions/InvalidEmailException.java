package com.europeandynamics.exceptions;

import com.europeandynamics.model.enums.HttpStatus;

public class InvalidEmailException extends RuntimeException {

	private HttpStatus httpStatus;
	public InvalidEmailException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("");
		sb.append(getMessage() + "| ");
		sb.append(httpStatus);
		return sb.toString();
	}
}
