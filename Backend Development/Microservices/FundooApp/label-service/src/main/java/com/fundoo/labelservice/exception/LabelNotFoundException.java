package com.fundoo.labelservice.exception;

public class LabelNotFoundException
        extends RuntimeException {

    public LabelNotFoundException(
            String message) {

        super(message);
    }
}