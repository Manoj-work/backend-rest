package com.example.backend.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    @Email(message = "Invalid email format!")
    @Indexed(unique = true)  // Ensure unique email in MongoDB
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    @Indexed(unique = true)  // Ensure unique phone in MongoDB
    @NotBlank(message = "Phone number cannot be empty")
    private String phone;

    private String department;

    @Pattern(regexp = "^(male|female|other)?$", message = "Gender must be male, female, or other")
    private String gender;

    private String reportingManager;
    private String permanentAddress;
    private String currentAddress;

    //ID Proofs Section
    @Valid
    private IdProofs idProofs = new IdProofs();

    //Bank Details Section
    @Valid
    private BankDetails bankDetails = new BankDetails();

    //Salary Details Section
    @Valid
    private SalaryDetails salaryDetails = new SalaryDetails();

    // Nested class for ID Proofs
    @Getter
    @Setter
    public static class IdProofs {
        @Pattern(regexp = "\\d{12}", message = "Aadhar number must be exactly 12 digits")
        private String aadharNo = "";

        @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number format")
        private String panNo = "";

        @Pattern(regexp = "^[A-Z]{1}[0-9]{7}$", message = "Invalid Passport number format")
        private String passport = "";

        @Pattern(regexp = "^[A-Za-z0-9]{8,16}$", message = "Invalid Driving License format")
        private String drivingLicense = "";

        @Pattern(regexp = "^[A-Z]{3}[0-9]{7}$", message = "Invalid Voter ID format")
        private String voterId = "";
    }

    // Nested class for Bank Details
    @Getter
    @Setter
    public static class BankDetails {
        @Pattern(regexp = "\\d{9,18}", message = "Account number must be between 9 to 18 digits")
        private String accountNumber = "";

//        @NotBlank(message = "Account holder name cannot be empty")
        private String accountHolderName = "";

        @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC Code format")
        private String ifscCode = "";

//        @NotBlank(message = "Bank name cannot be empty")
        private String bankName = "";

//        @NotBlank(message = "Branch name cannot be empty")
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
