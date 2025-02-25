package com.example.backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@Document(collection = "companies")  // Define MongoDB collection
public class CompanyModel {

    @Id
    private String id;  //  MongoDB uses String IDs by default

    @NotBlank(message = "Company name cannot be empty")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Indexed(unique = true)  // Ensure unique email in MongoDB
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    @Indexed(unique = true)  //  Ensure unique phone in MongoDB
    private String phone;

    @NotBlank(message = "GST number cannot be empty")
    @Size(min = 15, max = 15, message = "GST number must be exactly 15 characters")
    private String gst;

    @NotBlank(message = "Registration address cannot be empty")
    private String regAdd;
}
