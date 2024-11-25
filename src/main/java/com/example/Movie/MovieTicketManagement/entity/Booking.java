package com.example.Movie.MovieTicketManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Booking time cannot be null")
    @Future(message = "Booking time must be in the future")
    private LocalDateTime bookingTime;

    @ManyToOne
    @NotNull(message = "User cannot be null")
    private User user;

    @ManyToOne
    @NotNull(message = "Movie cannot be null")
    private Movie movie;

    @OneToOne
    @NotNull(message = "Seating layout cannot be null")
    private SeatingLayout seatingLayout;

    @OneToOne(mappedBy = "booking")
    private Payment payment;
}
