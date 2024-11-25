package com.example.Movie.MovieTicketManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seating_layout")
public class SeatingLayout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Seat number cannot be blank")
    private String seatNumber;

    private boolean isAvailable;

    @ManyToOne
    @NotNull(message = "Cinema hall cannot be null")
    private CinemaHall cinemaHall;
}
