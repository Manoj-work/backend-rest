package com.example.backend.service;

import com.example.backend.exception.DuplicateResourceException;
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
        if(userRepository.findByemail(user.getEmail()).isPresent()){
            throw new DuplicateResourceException("User With Email : " + user.getEmail() + " already exits");
        }
        if(userRepository.findByPhone(user.getPhone()).isPresent()){
            throw new DuplicateResourceException("User With Phone : " + user.getPhone() + " already exits");
        }
        return userRepository.save(user);
    }
}
