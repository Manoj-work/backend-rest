package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.repository.ModuleRepository;
import com.example.backend.model.ModuleModel;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<ModuleModel> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<ModuleModel> getModuleById(String id) {
        return moduleRepository.findById(id);
    }

    public ModuleModel createModule(ModuleModel module) {
        return moduleRepository.save(module);
    }

    public ModuleModel updateModule(String id, ModuleModel updatedModule) {
        return moduleRepository.findById(id).map(module -> {
            module.setModuleName(updatedModule.getModuleName());
            module.setDescription(updatedModule.getDescription());
            module.setAdmin(updatedModule.getAdmin());
            return moduleRepository.save(module);
        }).orElse(null);
    }

    public void deleteModule(String id) {
        moduleRepository.deleteById(id);
    }
}