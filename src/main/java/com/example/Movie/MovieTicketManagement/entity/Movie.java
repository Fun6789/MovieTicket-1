package com.example.Movie.MovieTicketManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 200, message = "Title must be 200 characters or fewer")
    private String title;

    @NotBlank(message = "Genre cannot be blank")
    @Size(max = 100, message = "Genre must be 100 characters or fewer")
    private String genre;

    @NotNull(message = "Release date cannot be null")
    @FutureOrPresent(message = "Release date must be in the present or future")
    private LocalDate releaseDate;

    @ManyToOne
    @NotNull(message = "Cinema hall cannot be null")
    private CinemaHall cinemaHall;
}


