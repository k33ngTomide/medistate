package com.medistate.patient.data.models;

import com.medistate.data.models.Gender;
import com.medistate.data.models.MedicalHistory;
import com.medistate.data.models.PackageType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Gender gender;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Patient blood group cannot be empty")
    private String bloodGroup;

    @NotEmpty(message = "Patient genotype cannot be empty")
    private String genotype;

    private LocalDate dateRegistered = LocalDate.now();

    @NotNull(message = "PackageType cannot be null")
    @Enumerated(EnumType.STRING)
    private PackageType patientPackage;

    @OneToMany
    private List<MedicalHistory> medicalHistory;

}
