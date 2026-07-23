package com.fundoo.notesservice.exception;

public class NoteNotInTrashException extends RuntimeException {

    public NoteNotInTrashException(String message) {
        super(message);
    }
}