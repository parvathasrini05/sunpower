package com.example.demo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository; // Missing import
import org.springframework.security.crypto.password.PasswordEncoder; // Missing import

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User register(User user) {
        // Check if user already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        
        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        if (passwordEncoder.matches(password, user.getPassword())) {
            return "Login successful for " + user.getRole();
        }
        
        throw new BadCredentialsException("Invalid credentials");
    }
    
    public boolean validateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElse(null);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}