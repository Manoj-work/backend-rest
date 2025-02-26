package com.example.backend.controller;

import com.example.backend.model.ModuleModel;
import com.example.backend.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/superadmin/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    // Get all modules with users
    @GetMapping
    public List<ModuleModel> getAllModules() {
        return moduleService.getAllModules();
    }

    // Create a new module and assign a user
    @PostMapping
    public ModuleModel createModule(@RequestBody Map<String, String> request) {
        return moduleService.createModule(
                request.get("moduleName"),
                request.get("description"),
                request.get("userId")
        );
    }
}
