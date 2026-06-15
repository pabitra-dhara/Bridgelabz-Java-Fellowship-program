package com.fundoonotesapp.fundoo.exception;


public class UserNotFoundException
        extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
