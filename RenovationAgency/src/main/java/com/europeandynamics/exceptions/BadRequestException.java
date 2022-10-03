package com.europeandynamics.exceptions;

import com.europeandynamics.model.enums.HttpStatus;

public class BadRequestException extends RuntimeException {

    private HttpStatus httpStatus;

    public BadRequestException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
