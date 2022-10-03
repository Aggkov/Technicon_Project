package com.europeandynamics.model.enums;

public enum HttpStatus {

    BAD_REQUEST(400),
    NOT_FOUND(404);

    private final int code;

    private HttpStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.getCode();
    }

    @Override
    public String toString() {
        return this.name() + " " + this.code;
    }
}
