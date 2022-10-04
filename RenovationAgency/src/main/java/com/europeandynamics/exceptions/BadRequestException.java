package com.europeandynamics.exceptions;

import com.europeandynamics.model.enums.HttpStatus;

public class BadRequestException extends RuntimeException {

    private HttpStatus httpStatus;

    public BadRequestException(String message,HttpStatus httpStatus) {
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
        sb.append(this.getHttpStatus());
        return sb.toString();
    }
}
