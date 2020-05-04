package com.thesis.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {

        BookNotFoundExceptionResponse bookNotFoundExceptionResponse = new BookNotFoundExceptionResponse(ex.getMessage());

        return new ResponseEntity(bookNotFoundExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleBookSearchNotFoundException(BookSearchNotFoundException ex, WebRequest request) {

        BookSearchNotFoundExceptionResponse bookSearchNotFoundExceptionResponse = new BookSearchNotFoundExceptionResponse(ex.getMessage());

        return new ResponseEntity(bookSearchNotFoundExceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
