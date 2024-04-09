package com.medistate.dtos.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;



@Data
public class AddPatientRequest {

    @NotEmpty(message = "Full name cannot be blank")
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;


    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$", message = "Password must contain at least one letter and one digit")
    private String password;


    @NotEmpty(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number format")
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    @Past(message = "Date of birth must be in the past")
    private String dateOfBirth;

    @NotNull(message = "Gender cannot be null")
    @Enumerated(EnumType.STRING)
    private String gender;

    @NotEmpty(message = "Patient blood group cannot be empty")
    private String bloodGroup;

    @NotEmpty(message = "Patient genotype cannot be empty")
    private String genotype;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Weight cannot be empty")
    private String weight;

    @NotEmpty(message = "Hospital Name Cannot be empty")
    private String hospitalName;

    @NotEmpty(message = "package cannot be empty")
    private String patientPackage;

}
