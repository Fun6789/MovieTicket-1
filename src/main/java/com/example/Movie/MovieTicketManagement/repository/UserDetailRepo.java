package com.example.Movie.MovieTicketManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Movie.MovieTicketManagement.entity.User;

public interface UserDetailRepo extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
}

