package com.example.Movie.MovieTicketManagement.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Movie.MovieTicketManagement.entity.User;
import com.example.Movie.MovieTicketManagement.exception.UserConflictException;
import com.example.Movie.MovieTicketManagement.exception.UserNotFoundException;
import com.example.Movie.MovieTicketManagement.repository.UserDetailRepo;
import com.example.Movie.MovieTicketManagement.service.UserDetail;

@Service
public class UserDetailImpl implements UserDetail {

	 @Autowired
    private final UserDetailRepo userRepository;

   
    public UserDetailImpl(UserDetailRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
        return user.get();
    }

    @Override
    public User saveUser(User user) throws UserConflictException {
        // Check for conflicts (e.g., user email, username)
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserConflictException("User with email " + user.getEmail() + " already exists.");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) throws UserNotFoundException, UserConflictException {
        User existingUser = getUserById(id); // Will throw exception if not found
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());

        // Check for conflicts again before updating
        Optional<User> conflictingUser = userRepository.findByEmail(user.getEmail());
        if (conflictingUser.isPresent() && !conflictingUser.get().getId().equals(id)) {
            throw new UserConflictException("User with the same email already exists.");
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        User user = getUserById(id);  // Will throw exception if not found
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);  // assuming email as username
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // You can add roles or authorities here if needed
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), List.of());
        } else {
            throw new UsernameNotFoundException("User with email " + username + " not found.");
        }
    }
}
