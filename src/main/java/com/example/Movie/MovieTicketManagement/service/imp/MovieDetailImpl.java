package com.example.Movie.MovieTicketManagement.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Movie.MovieTicketManagement.entity.Movie;
import com.example.Movie.MovieTicketManagement.exception.UserConflictException;
import com.example.Movie.MovieTicketManagement.exception.UserNotFoundException;
import com.example.Movie.MovieTicketManagement.repository.MovieRepository;
import com.example.Movie.MovieTicketManagement.service.MovieDetail;

@Service
public class MovieDetailImpl implements MovieDetail {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieDetailImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie getMovieById(Long id) throws UserNotFoundException {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new UserNotFoundException("Movie with ID " + id + " not found.");
        }
        return movie.get();
    }

    @Override
    public Movie saveMovie(Movie movie) throws UserConflictException {
        // Check for conflicts (e.g., movie title)
        Optional<Movie> existingMovie = movieRepository.findByTitle(movie.getTitle());
        if (existingMovie.isPresent()) {
            throw new UserConflictException("Movie with title " + movie.getTitle() + " already exists.");
        }
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) throws UserNotFoundException, UserConflictException {
        Movie existingMovie = getMovieById(id); // Will throw exception if not found
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setReleaseDate(movie.getReleaseDate());

        // Check for conflicts again before updating
        Optional<Movie> conflictingMovie = movieRepository.findByTitle(movie.getTitle());
        if (conflictingMovie.isPresent() && !conflictingMovie.get().getId().equals(id)) {
            throw new UserConflictException("Movie with the same title already exists.");
        }

        return movieRepository.save(existingMovie);
    }

    @Override
    public void deleteMovie(Long id) throws UserNotFoundException {
        Movie movie = getMovieById(id);  // Will throw exception if not found
        movieRepository.delete(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
