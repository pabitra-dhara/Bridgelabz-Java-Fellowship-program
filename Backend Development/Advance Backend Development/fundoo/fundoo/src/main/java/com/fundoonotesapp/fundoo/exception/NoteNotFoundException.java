package com.fundoonotesapp.fundoo.exception;

public class NoteNotFoundException
        extends RuntimeException {

    public NoteNotFoundException(String message) {
        super(message);
    }
}