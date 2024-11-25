package com.example.Movie.MovieTicketManagement.service;


import java.util.List;

import com.example.Movie.MovieTicketManagement.entity.Booking;
import com.example.Movie.MovieTicketManagement.exception.BookingAlreadyExistsException;
import com.example.Movie.MovieTicketManagement.exception.EntityNotFoundException;
import com.example.Movie.MovieTicketManagement.exception.InvalidInputException;
import com.example.Movie.MovieTicketManagement.exception.PaymentProcessingException;
import com.example.Movie.MovieTicketManagement.exception.SeatNotAvailableException;
import com.example.Movie.MovieTicketManagement.exception.UnauthorizedAccessException;

public interface BookingService {

    Booking createBooking(Booking booking) throws InvalidInputException, SeatNotAvailableException, BookingAlreadyExistsException;

    
    Booking getBookingById(Long bookingId) throws EntityNotFoundException;

   
    Booking updateBooking(Long bookingId, Booking booking) throws EntityNotFoundException, InvalidInputException, SeatNotAvailableException;

   
    boolean processPayment(Long bookingId, double paymentAmount) throws PaymentProcessingException, UnauthorizedAccessException;
    
    
    void cancelBooking(Long bookingId) throws EntityNotFoundException;
    
   
    List<Booking> getBookingsByUser(Long userId) throws EntityNotFoundException;
}