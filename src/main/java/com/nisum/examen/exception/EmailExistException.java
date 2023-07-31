package com.nisum.examen.exception;

public class EmailExistException extends RuntimeException{
    public EmailExistException(String message) {
        super(message);
    }

}
