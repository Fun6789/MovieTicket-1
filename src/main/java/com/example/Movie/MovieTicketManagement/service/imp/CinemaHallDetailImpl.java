package com.example.Movie.MovieTicketManagement.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Movie.MovieTicketManagement.entity.CinemaHall;
import com.example.Movie.MovieTicketManagement.exception.CinemaHallNotFoundException;
import com.example.Movie.MovieTicketManagement.repository.CinemaHallDetailRepository;
import com.example.Movie.MovieTicketManagement.service.CinemaHallDetail;

@Service
public class CinemaHallDetailImpl implements CinemaHallDetail {

	@Autowired
    private final CinemaHallDetailRepository cinemaHallRepository;

    
    public CinemaHallDetailImpl(CinemaHallDetailRepository cinemaHallRepository) {
        this.cinemaHallRepository = cinemaHallRepository;
    }

    @Override
    public CinemaHall getCinemaHallById(Long id) throws CinemaHallNotFoundException {
        Optional<CinemaHall> cinemaHall = cinemaHallRepository.findById(id);
        if (cinemaHall.isEmpty()) {
            throw new CinemaHallNotFoundException("CinemaHall with ID " + id + " not found.");
        }
        return cinemaHall.get();
    }

    @Override
    public CinemaHall saveCinemaHall(CinemaHall cinemaHall) {
        // Check for conflicts (e.g., same name and location)
        Optional<CinemaHall> existingCinemaHall = cinemaHallRepository
                .findByNameAndLocation(cinemaHall.getName(), cinemaHall.getLocation());
        
        return cinemaHallRepository.save(cinemaHall);
    }

    @Override
    public CinemaHall updateCinemaHall(Long id, CinemaHall cinemaHall) throws CinemaHallNotFoundException {
        CinemaHall existingCinemaHall = getCinemaHallById(id); // Will throw exception if not found
        existingCinemaHall.setName(cinemaHall.getName());
        existingCinemaHall.setLocation(cinemaHall.getLocation());

        // Check for conflicts again before updating
        Optional<CinemaHall> conflictingCinemaHall = cinemaHallRepository
                .findByNameAndLocation(cinemaHall.getName(), cinemaHall.getLocation());
        
        
        return cinemaHallRepository.save(existingCinemaHall);
    }

    @Override
    public void deleteCinemaHall(Long id) throws CinemaHallNotFoundException {
        CinemaHall cinemaHall = getCinemaHallById(id);  // Will throw exception if not found
        cinemaHallRepository.delete(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAllCinemaHalls() {
        return cinemaHallRepository.findAll();
    }
}