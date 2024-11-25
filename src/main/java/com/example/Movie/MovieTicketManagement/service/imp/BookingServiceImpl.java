package com.example.Movie.MovieTicketManagement.service.imp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Movie.MovieTicketManagement.entity.Booking;
import com.example.Movie.MovieTicketManagement.entity.Movie;
import com.example.Movie.MovieTicketManagement.entity.SeatingLayout;
import com.example.Movie.MovieTicketManagement.entity.User;
import com.example.Movie.MovieTicketManagement.exception.BookingAlreadyExistsException;
import com.example.Movie.MovieTicketManagement.exception.EntityNotFoundException;
import com.example.Movie.MovieTicketManagement.exception.InvalidInputException;
import com.example.Movie.MovieTicketManagement.exception.PaymentProcessingException;
import com.example.Movie.MovieTicketManagement.exception.SeatNotAvailableException;
import com.example.Movie.MovieTicketManagement.exception.UnauthorizedAccessException;
import com.example.Movie.MovieTicketManagement.repository.BookingRepository;
import com.example.Movie.MovieTicketManagement.repository.MovieRepository;
import com.example.Movie.MovieTicketManagement.repository.SeatingLayoutRepository;
import com.example.Movie.MovieTicketManagement.repository.UserDetailRepo;
import com.example.Movie.MovieTicketManagement.service.BookingService;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SeatingLayoutRepository seatingLayoutRepository;

    @Autowired
    private UserDetailRepo userRepository;

    @Override
    public Booking createBooking(Booking booking) throws InvalidInputException, SeatNotAvailableException, BookingAlreadyExistsException {
        // Validate input data
        if (booking == null || booking.getUser() == null || booking.getSeatingLayout() == null || booking.getMovie() == null) {
            throw new InvalidInputException("Booking details are invalid.");
        }

        // Check if the seat is available
        Optional<SeatingLayout> seatingLayoutOptional = seatingLayoutRepository.findById(booking.getSeatingLayout().getId());
        if (seatingLayoutOptional.isEmpty() || !seatingLayoutOptional.get().isAvailable()) {
            throw new SeatNotAvailableException("The seat is not available.");
        }

        // Check if booking already exists
        Optional<Booking> existingBooking = bookingRepository.findBySeatingLayoutAndUser(booking.getSeatingLayout(), booking.getUser());
        if (existingBooking.isPresent()) {
            throw new BookingAlreadyExistsException("A booking already exists for this seat.");
        }

        // Save the booking
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) throws EntityNotFoundException {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("Booking not found for ID: " + bookingId));
    }

    @Override
    public Booking updateBooking(Long bookingId, Booking booking) throws EntityNotFoundException, InvalidInputException, SeatNotAvailableException {
       
        Booking existingBooking = getBookingById(bookingId);

      
        if (booking == null || booking.getUser() == null || booking.getSeatingLayout() == null || booking.getMovie() == null) {
            throw new InvalidInputException("Updated booking details are invalid.");
        }

       
        Optional<SeatingLayout> seatingLayoutOptional = seatingLayoutRepository.findById(booking.getSeatingLayout().getId());
        if (seatingLayoutOptional.isEmpty() || !seatingLayoutOptional.get().isAvailable()) {
            throw new SeatNotAvailableException("The new seat is not available.");
        }

        // Update the booking
        existingBooking.setUser(booking.getUser());
        existingBooking.setSeatingLayout(booking.getSeatingLayout());
        existingBooking.setMovie(booking.getMovie());
        existingBooking.setBookingTime(booking.getBookingTime());
        return bookingRepository.save(existingBooking);
    }

    @Override
    public boolean processPayment(Long bookingId, double paymentAmount) throws PaymentProcessingException {
       Booking booking = getBookingById(bookingId);
       if (paymentAmount <= 0) {
           throw new PaymentProcessingException("Invalid payment amount.");
       }
      

        
        return true;
    }

    @Override
    public void cancelBooking(Long bookingId) throws EntityNotFoundException {
        // Retrieve booking and cancel
        Booking booking = getBookingById(bookingId);
        bookingRepository.delete(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) throws EntityNotFoundException {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        if (bookings.isEmpty()) {
            throw new EntityNotFoundException("No bookings found for user with ID: " + userId);
        }
        return bookings;
    }
}