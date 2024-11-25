package com.example.Movie.MovieTicketManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Movie.MovieTicketManagement.entity.CinemaHall;

public interface CinemaHallDetailRepository extends JpaRepository<CinemaHall, Long> {

	Optional<CinemaHall> findByNameAndLocation(String name, String location); }
