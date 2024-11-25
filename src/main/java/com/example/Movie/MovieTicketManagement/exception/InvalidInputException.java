package com.example.Movie.MovieTicketManagement.exception;

@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
