package com.thoughtworks.capacity.gtb.mvc.exception;

public class RegisterUsernameDuplicatedError extends RuntimeException {
    public RegisterUsernameDuplicatedError(String message) {
        super(message);
    }
}
