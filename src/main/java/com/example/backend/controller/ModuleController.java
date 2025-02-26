package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.backend.service.ModuleService;
import com.example.backend.model.ModuleModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/superadmin/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping
    public ResponseEntity<List<ModuleModel>> getAllModules() {
        return ResponseEntity.ok(moduleService.getAllModules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleModel> getModuleById(@PathVariable String id) {
        Optional<ModuleModel> module = moduleService.getModuleById(id);
        return module.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ModuleModel> createModule(@RequestBody ModuleModel module) {
        return new ResponseEntity<>(moduleService.createModule(module), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuleModel> updateModule(@PathVariable String id, @RequestBody ModuleModel updatedModule) {
        ModuleModel module = moduleService.updateModule(id, updatedModule);
        return module != null ? ResponseEntity.ok(module) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable String id) {
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }
}