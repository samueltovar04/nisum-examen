package com.nisum.examen.exception;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(InvalidJodException.class)
    public ResponseEntity<ErrorMessage> emailNotFoundException(InvalidJodException ex) {
        return new ResponseEntity<>(
                ErrorMessage.builder()
                        .message(ex.getMessage()).build(), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<ErrorMessage> emailNotFoundException(EmailExistException ex) {
        return new ResponseEntity<>(
                ErrorMessage.builder()
                        .message(ex.getMessage()).build(), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> pmsBadRequestException(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                ErrorMessage.builder()
                        .message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex) {
        return new ResponseEntity<>(
                ErrorMessage.builder()
                        .message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<ErrorMessage> exceptionHandler(IOException e) {
        return new ResponseEntity<>(
                ErrorMessage.builder()
                        .message(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMessage> invalidCredentials(Throwable e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(new ErrorMessage(e.getMessage()));
    }
}
