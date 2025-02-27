package com.example.backend.repository;

import com.example.backend.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel, String> {
    Optional<UserModel> findByemail(String email);
    Optional<UserModel> findByPhone(String phone);
}
