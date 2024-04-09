package com.medistate.exceptions;

public class PatientExistException extends AdminExistException {
    public PatientExistException(String message) {
        super(message);
    }
}
