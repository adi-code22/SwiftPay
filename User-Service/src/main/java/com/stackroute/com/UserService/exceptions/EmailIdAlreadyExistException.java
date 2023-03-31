package com.stackroute.com.UserService.exceptions;

public class EmailIdAlreadyExistException extends Exception {
    public EmailIdAlreadyExistException() {
    }
    public EmailIdAlreadyExistException(String message) {
        super(message);
    }
}
