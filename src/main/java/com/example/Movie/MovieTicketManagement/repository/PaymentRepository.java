package com.example.Movie.MovieTicketManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Movie.MovieTicketManagement.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>  {

	Payment findByBookingId(Long bookingId);

}
