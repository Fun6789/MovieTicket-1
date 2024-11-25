package com.example.Movie.MovieTicketManagement.service;

import java.util.List;

import com.example.Movie.MovieTicketManagement.entity.User;
import com.example.Movie.MovieTicketManagement.exception.UserConflictException;
import com.example.Movie.MovieTicketManagement.exception.UserNotFoundException;

public interface UserDetail {

    // Get a User by ID
    User getUserById(Long id) throws UserNotFoundException;

    // Save a new User
    User saveUser(User user) throws UserConflictException;

    // Update an existing User
    User updateUser(Long id, User user) throws UserNotFoundException, UserConflictException;

    // Delete a User by ID
    void deleteUser(Long id) throws UserNotFoundException;

    // List all Users
    List<User> getAllUsers();
}
