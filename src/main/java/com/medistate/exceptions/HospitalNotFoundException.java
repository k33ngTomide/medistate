package com.medistate.exceptions;

public class HospitalNotFoundException extends MediStateException {

    public HospitalNotFoundException() {
    }

    public HospitalNotFoundException(String message) {
        super(message);
    }

    public HospitalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
