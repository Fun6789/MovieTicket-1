package com.example.Movie.MovieTicketManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Movie.MovieTicketManagement.entity.CinemaHall;
import com.example.Movie.MovieTicketManagement.service.CinemaHallDetail;

@RestController
@RequestMapping("/api/cinemaHalls")
public class CinemaHallController {

	@Autowired
    private final CinemaHallDetail cinemaHallDetailService;

    
    public CinemaHallController(CinemaHallDetail cinemaHallDetailService) {
        this.cinemaHallDetailService = cinemaHallDetailService;
    }

    @GetMapping("/{id}")
    public CinemaHall getCinemaHall(@PathVariable Long id) {
        return cinemaHallDetailService.getCinemaHallById(id);
    }

    @PostMapping
    
    public CinemaHall createCinemaHall(@RequestBody CinemaHall cinemaHall) {
        return cinemaHallDetailService.saveCinemaHall(cinemaHall);
    }

    @PutMapping("/{id}")
    public CinemaHall updateCinemaHall(@PathVariable Long id, @RequestBody CinemaHall cinemaHall) {
        return cinemaHallDetailService.updateCinemaHall(id, cinemaHall);
    }

    @DeleteMapping("/{id}")
    public void deleteCinemaHall(@PathVariable Long id) {
        cinemaHallDetailService.deleteCinemaHall(id);
    }

    @GetMapping
    public List<CinemaHall> getAllCinemaHalls() {
        return cinemaHallDetailService.getAllCinemaHalls();
    }
}
