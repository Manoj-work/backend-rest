package com.example.backend.repository;

import com.example.backend.model.EmployeeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeModel, String> {
}
