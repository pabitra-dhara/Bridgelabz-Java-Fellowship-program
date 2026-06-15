package com.fundoonotesapp.fundoo.exception;

import com.fundoonotesapp.fundoo.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleUserExists(
            UserAlreadyExistsException ex) {

        return new ApiResponse<>(
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<String> handleUserNotFound(
            UserNotFoundException ex) {

        return new ApiResponse<>(
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<String> handleNoteException(
            NoteNotFoundException ex){

        return new ApiResponse<>(
                ex.getMessage(),
                null
        );
    }

}
//google auth
//smtp

