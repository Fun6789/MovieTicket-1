package com.example.Movie.MovieTicketManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_details")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Password cannot be blank")
    private String password;
    
    @NotBlank(message = "Name cannot be blank")
    private String name;
    
    @NotBlank(message = "Username cannot be blank")
    private String username;
    
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;
    
    @NotBlank(message = "Role cannot be blank")
    private String role; // e.g., ROLE_USER or ROLE_ADMIN
}
