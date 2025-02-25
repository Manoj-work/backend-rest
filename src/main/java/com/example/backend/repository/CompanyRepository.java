package com.example.backend.repository;

import com.example.backend.model.CompanyModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CompanyRepository extends MongoRepository<CompanyModel, String> {
    Optional<CompanyModel> findByEmail(String email);
    Optional<CompanyModel> findByPhone(String phone);
}
