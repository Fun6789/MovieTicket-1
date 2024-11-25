package com.example.Movie.MovieTicketManagement.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Movie.MovieTicketManagement.entity.Booking;
import com.example.Movie.MovieTicketManagement.entity.Payment;
import com.example.Movie.MovieTicketManagement.exception.EntityNotFoundException;
import com.example.Movie.MovieTicketManagement.exception.PaymentProcessingException;
import com.example.Movie.MovieTicketManagement.repository.BookingRepository;
import com.example.Movie.MovieTicketManagement.repository.PaymentRepository;
import com.example.Movie.MovieTicketManagement.service.PaymentService;




@Service
public class PaymentDetailImpl implements PaymentService {

	@Autowired
    private final PaymentRepository paymentRepository;

    
    public PaymentDetailImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public boolean processPayment(Long bookingId, double paymentAmount) throws PaymentProcessingException {
        // Fetch the payment from the repository using the bookingId
        Payment payment = paymentRepository.findByBookingId(bookingId);

        if (payment == null) {
            throw new PaymentProcessingException("Payment not found for booking with ID " + bookingId);
        }

        if (payment.getAmount() != paymentAmount) {
            throw new PaymentProcessingException("Payment amount does not match the required amount.");
        }

        
            payment.setPaymentStatus("SUCCESS");
            paymentRepository.save(payment);
            return true;
        } 
    

    @Override
    public Payment getPaymentById(Long id) throws EntityNotFoundException {
        
        Payment payment = paymentRepository.findById(id).orElse(null);

        if (payment == null) {
            throw new EntityNotFoundException("Payment not found with ID " + id);
        }

        return payment;
    }

    @Override
    public Payment savePayment(Payment payment) {
      
            return paymentRepository.save(payment);
       
    }

    @Override
    public Payment updatePayment(Long id, Payment payment) throws EntityNotFoundException{
        // Fetch the existing payment by ID
        Payment existingPayment = getPaymentById(id); 
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentStatus(payment.getPaymentStatus());

        return paymentRepository.save(existingPayment);
        
    }

    @Override
    public void deletePayment(Long id) throws EntityNotFoundException {
        Payment payment = getPaymentById(id); // Will throw EntityNotFoundException if not found
        paymentRepository.delete(payment);
    }
}
