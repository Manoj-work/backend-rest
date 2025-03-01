package com.example.backend.service;

import com.example.backend.config.JwtUtil;
import com.example.backend.dto.AuthRequest;
import com.example.backend.dto.AuthResponse;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.exception.DuplicateResourceException;
import com.example.backend.model.Role;
import com.example.backend.model.UserAccount;
import com.example.backend.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAccountRepository userAccountRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(RegisterRequest request) {
        if (userAccountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User already exists with this email id");
        }

        UserAccount user = UserAccount.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles()) // Set<Role> directly stored
                .build();

        userAccountRepository.save(user);

        return "User registered successfully!";
    }

    public AuthResponse login(AuthRequest request) {
        Optional<UserAccount> userOpt = userAccountRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            throw new BadCredentialsException("Invalid Email or Password");
        }

        UserAccount user = userOpt.get();
        Set<Role> roles = user.getRoles(); // Get roles directly as Set<Role>

        String token = jwtUtil.generateToken(user.getEmail(), roles); // Pass Set<Role> directly
        return new AuthResponse(token);
    }
}
