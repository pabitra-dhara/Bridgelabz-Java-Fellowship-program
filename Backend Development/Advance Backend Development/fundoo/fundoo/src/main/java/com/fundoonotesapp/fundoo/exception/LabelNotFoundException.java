package com.fundoonotesapp.fundoo.exception;

public class LabelNotFoundException
        extends RuntimeException {

    public LabelNotFoundException(
            String message) {

        super(message);
    }
}