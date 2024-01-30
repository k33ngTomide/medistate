package com.medistate.exceptions;

public class MediStateException  extends RuntimeException{

    public MediStateException() {
    }

    public MediStateException(String message) {
        super(message);
    }

    public MediStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public MediStateException(Throwable cause) {
        super(cause);
    }

}
