package com.example.Movie.MovieTicketManagement.service;

import java.util.List;

import com.example.Movie.MovieTicketManagement.entity.CinemaHall;
import com.example.Movie.MovieTicketManagement.exception.CinemaHallNotFoundException;

public interface CinemaHallDetail {

    // Get a CinemaHall by ID
    CinemaHall getCinemaHallById(Long id) throws CinemaHallNotFoundException;

    // Save a new CinemaHall
    CinemaHall saveCinemaHall(CinemaHall cinemaHall);

    // Update an existing CinemaHall
    CinemaHall updateCinemaHall(Long id, CinemaHall cinemaHall) throws CinemaHallNotFoundException;

    // Delete a CinemaHall by ID
    void deleteCinemaHall(Long id) throws CinemaHallNotFoundException;

    // List all CinemaHalls
    List<CinemaHall> getAllCinemaHalls();
}