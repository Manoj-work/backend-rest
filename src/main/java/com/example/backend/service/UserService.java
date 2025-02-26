package com.example.backend.service;

import com.example.backend.model.UserModel;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a user
    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }
}
