package com.example.backend.repository;

import com.example.backend.model.ModuleModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModuleRepository extends MongoRepository<ModuleModel, String> {}