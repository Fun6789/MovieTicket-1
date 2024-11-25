package com.example.Movie.MovieTicketManagement.exception;

@SuppressWarnings("serial")
public class BookingAlreadyExistsException extends RuntimeException {

    public BookingAlreadyExistsException(String message) {
        super(message);
    }

    public BookingAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
