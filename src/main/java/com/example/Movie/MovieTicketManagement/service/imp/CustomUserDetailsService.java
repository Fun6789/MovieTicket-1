package com.example.Movie.MovieTicketManagement.service.imp;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDetailImpl userDetailImpl;

    public CustomUserDetailsService(UserDetailImpl userDetailImpl) {
        this.userDetailImpl = userDetailImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.Movie.MovieTicketManagement.entity.User user =(com.example.Movie.MovieTicketManagement.entity.User) userDetailImpl.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
