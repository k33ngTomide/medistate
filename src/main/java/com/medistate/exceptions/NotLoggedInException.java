package com.medistate.exceptions;

public class NotLoggedInException extends AdminExistException{
    public NotLoggedInException(String message) {
        super(message);
    }
}
