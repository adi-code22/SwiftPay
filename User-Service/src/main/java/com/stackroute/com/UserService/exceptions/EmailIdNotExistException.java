package com.stackroute.com.UserService.exceptions;

public class EmailIdNotExistException extends Exception {
    public EmailIdNotExistException() {
    }
    public EmailIdNotExistException(String message) {
        super(message);
    }
}
