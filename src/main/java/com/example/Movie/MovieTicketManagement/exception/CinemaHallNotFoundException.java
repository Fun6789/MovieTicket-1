package com.example.Movie.MovieTicketManagement.exception;

public class CinemaHallNotFoundException extends RuntimeException {
    public CinemaHallNotFoundException(String message) {
        super(message);
    }
}