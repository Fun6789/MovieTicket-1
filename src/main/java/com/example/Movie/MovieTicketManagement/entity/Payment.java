package com.example.Movie.MovieTicketManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Amount must be positive or zero")
    private double amount;

    @NotBlank(message = "Payment status cannot be blank")
    private String paymentStatus;

    @OneToOne
    @NotNull(message = "Booking cannot be null")
    private Booking booking;
}

