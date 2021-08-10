package com.tommy.exception;

public class SignUpException extends RuntimeException {

    public SignUpException() {
        super();
    }

    public SignUpException(String message) {
        super(message);
    }

}
