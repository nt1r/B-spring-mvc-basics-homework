package com.thoughtworks.capacity.gtb.mvc.exception;

public class PasswordNotCorrectException extends RuntimeException {
    public PasswordNotCorrectException(String message) {
        super(message);
    }
}
