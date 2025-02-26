package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "modules")
public class ModuleModel {

    @Id
    private String id;

    @NotEmpty(message = "Module name cannot be empty")
    private String moduleName;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @DBRef
    @NotEmpty(message = "Admin cannot be empty")
    private UserModel admin;

    public ModuleModel() {}

    public ModuleModel(String moduleName, String description, UserModel admin) {
        this.moduleName = moduleName;
        this.description = description;
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getAdmin() {
        return admin;
    }

    public void setAdmin(UserModel admin) {
        this.admin = admin;
    }
}