package com.example.backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "employees")  // Define MongoDB collection
public class EmployeeModel {

    @Id
    private String id;  // MongoDB uses String IDs by default

    @NotBlank(message = "Employee name cannot be empty")
    private String name;

    private String title; // Job Title

    @Email(message = "Invalid email format")
    @Indexed(unique = true)  // Ensure unique email in MongoDB
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    @Indexed(unique = true)  // Ensure unique phone in MongoDB
    private String phone;

    private String department;

    @Pattern(regexp = "^(male|female|other)?$", message = "Gender must be male, female, or other")
    private String gender;

    private String reportingManager;
    private String permanentAddress;
    private String currentAddress;

    // ✅ ID Proofs Section
    private IdProofs idProofs = new IdProofs();

    // ✅ Bank Details Section
    private BankDetails bankDetails = new BankDetails();

    // ✅ Salary Details Section
    private SalaryDetails salaryDetails = new SalaryDetails();

    // Nested class for ID Proofs
    @Getter
    @Setter
    public static class IdProofs {
        private String aadharNo = "";
        private String panNo = "";
        private String passport = "";
        private String drivingLicense = "";
        private String voterId = "";
    }

    // Nested class for Bank Details
    @Getter
    @Setter
    public static class BankDetails {
        private String accountNumber = "";
        private String accountHolderName = "";
        private String ifscCode = "";
        private String bankName = "";
        private String branchName = "";
    }

    // Nested class for Salary Details
    @Getter
    @Setter
    public static class SalaryDetails {
        private Double totalCtc = 0.0;
        private Double basic = 0.0;
        private Double allowances = 0.0;
        private Double hra = 0.0;
        private Double pf = 0.0;
    }
}
