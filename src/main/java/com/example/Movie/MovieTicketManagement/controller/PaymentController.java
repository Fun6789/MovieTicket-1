package com.example.Movie.MovieTicketManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Movie.MovieTicketManagement.entity.Payment;
import com.example.Movie.MovieTicketManagement.exception.EntityNotFoundException;
import com.example.Movie.MovieTicketManagement.exception.PaymentProcessingException;
import com.example.Movie.MovieTicketManagement.service.imp.PaymentDetailImpl;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
    private final PaymentDetailImpl paymentDetailService;

    
    public PaymentController(PaymentDetailImpl paymentDetailService) {
        this.paymentDetailService = paymentDetailService;
    }

    @PostMapping("/process/{bookingId}")
    public boolean processPayment(@PathVariable Long bookingId, @RequestParam double paymentAmount) throws PaymentProcessingException {
        return paymentDetailService.processPayment(bookingId, paymentAmount);
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) throws EntityNotFoundException {
        return paymentDetailService.getPaymentById(id);
    }

    @PostMapping
   
    public Payment createPayment(@RequestBody Payment payment){
        return paymentDetailService.savePayment(payment);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) throws EntityNotFoundException {
        return paymentDetailService.updatePayment(id, payment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) throws EntityNotFoundException {
        paymentDetailService.deletePayment(id);
    }
}