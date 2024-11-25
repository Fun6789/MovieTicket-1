package com.example.Movie.MovieTicketManagement.service;

import java.util.List;

import com.example.Movie.MovieTicketManagement.entity.Movie;
import com.example.Movie.MovieTicketManagement.exception.UserConflictException;
import com.example.Movie.MovieTicketManagement.exception.UserNotFoundException;

public interface MovieDetail {

	Movie getMovieById(Long id) throws UserNotFoundException;

    // Save a new Movie
    Movie saveMovie(Movie movie) throws UserConflictException;

    // Update an existing Movie
    Movie updateMovie(Long id, Movie movie) throws UserNotFoundException, UserConflictException;

    // Delete a Movie by ID
    void deleteMovie(Long id) throws UserNotFoundException;

    // List all Movies
    List<Movie> getAllMovies();
}
