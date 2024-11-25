package com.example.Movie.MovieTicketManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Movie.MovieTicketManagement.entity.Movie;
import com.example.Movie.MovieTicketManagement.service.MovieDetail;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
	@Autowired
    private final MovieDetail movieDetailService;

    
    public MovieController(MovieDetail movieDetailService) {
        this.movieDetailService = movieDetailService;
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieDetailService.getMovieById(id);
    }

    @PostMapping
   
    public Movie createMovie(@RequestBody Movie movie) {
        return movieDetailService.saveMovie(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return movieDetailService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieDetailService.deleteMovie(id);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieDetailService.getAllMovies();
    }
}
