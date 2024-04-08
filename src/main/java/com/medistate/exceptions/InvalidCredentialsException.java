package com.medistate.exceptions;

public class InvalidCredentialsException extends AdminExistException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
