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

    // Get all modules with user details
    public List<ModuleModel> getAllModules() {
        return moduleRepository.findAll();
    }

    // Create a module and assign a user
    public ModuleModel createModule(String moduleName, String description, String userId) {
        UserModel user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        ModuleModel module = new ModuleModel();
        module.setModuleName(moduleName);
        module.setDescription(description);
        module.setUser(user);

        return moduleRepository.save(module);
    }
}
