package com.medistate.dtos.request;

import com.medistate.data.models.Gender;
import com.medistate.data.models.MedicalHistory;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AddPatientRequest {

    @NotEmpty(message = "Full name cannot be blank")
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

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

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Hospital Name Cannot be empty")
    private String hospitalName;

}
