package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Document(collection = "modules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ModuleModel {

    @Id
    private String id;

    @NotEmpty(message = "Module name cannot be empty")
    private String moduleName;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @DBRef
    private UserModel user; // Store a single user as admin
}
