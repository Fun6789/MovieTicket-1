package com.example.Movie.MovieTicketManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Movie.MovieTicketManagement.entity.Booking;
import com.example.Movie.MovieTicketManagement.entity.SeatingLayout;
import com.example.Movie.MovieTicketManagement.entity.User;

public interface BookingRepository extends JpaRepository<Booking, Long>  {
	Optional<Booking> findBySeatingLayoutAndUser(SeatingLayout seatingLayout, User user);

	 List<Booking> findByUserId(Long userId);
}
