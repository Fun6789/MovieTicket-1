package com.example.Movie.MovieTicketManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Movie.MovieTicketManagement.entity.User;
import com.example.Movie.MovieTicketManagement.service.UserDetail;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

	  @Autowired
    private final UserDetail userDetailService;
	  private final PasswordEncoder passwordEncoder;

  
    public UserController(UserDetail userDetailService, PasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password
        userDetailService.saveUser(user);
        return "User registered successfully";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userDetailService.getUserById(id);
    }

//    @PostMapping
//    public User createUser(@Valid @RequestBody User user) {
//        return userDetailService.saveUser(user);
//    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userDetailService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDetailService.deleteUser(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userDetailService.getAllUsers();
    }
}
