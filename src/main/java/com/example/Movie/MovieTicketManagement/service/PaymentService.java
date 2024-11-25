package com.example.Movie.MovieTicketManagement.service;

import org.hibernate.exception.GenericJDBCException;

import com.example.Movie.MovieTicketManagement.entity.Payment;
import com.example.Movie.MovieTicketManagement.exception.EntityNotFoundException;
import com.example.Movie.MovieTicketManagement.exception.PaymentProcessingException;

public interface PaymentService {
	 boolean processPayment(Long bookingId, double paymentAmount) throws PaymentProcessingException;

	    // Get Payment by ID
	    Payment getPaymentById(Long id) throws EntityNotFoundException;

	    // Save a new Payment
	    Payment savePayment(Payment payment) throws GenericJDBCException;

	    // Update an existing Payment
	    Payment updatePayment(Long id, Payment payment) throws EntityNotFoundException, GenericJDBCException;

	    // Delete Payment by ID
	    void deletePayment(Long id) throws EntityNotFoundException;
}
