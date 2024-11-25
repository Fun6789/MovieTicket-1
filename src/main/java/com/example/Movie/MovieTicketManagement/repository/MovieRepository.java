package com.example.Movie.MovieTicketManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Movie.MovieTicketManagement.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

	 Optional<Movie> findByTitle(String title);

}
