package com.example.backend.service;

import com.example.backend.model.ModuleModel;
import com.example.backend.model.UserModel;
import com.example.backend.repository.ModuleRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private UserRepository userRepository;

    public ModuleModel createModule(ModuleModel moduleModel) {
        // Fetch UserModel using userId
        UserModel user = userRepository.findById(moduleModel.getUserId())
                .orElseThrow(() -> new RuntimeException("User with ID " + moduleModel.getUserId() + " not found"));

        moduleModel.setUser(user); // Set the fetched user
        moduleModel.setUserId(moduleModel.getUserId()); // Clear userId as it's not needed in DB

        return moduleRepository.save(moduleModel);
    }

    public List<ModuleModel> getAllModules() {
        return moduleRepository.findAll();
    }
}
