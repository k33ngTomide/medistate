package com.medistate.data.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class Hospital {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty(message = "Hospital Name cannot be empty")
    @Size(min = 2, message = "Hospital Name must be more than 1letter")
    @Column(unique = true)
    private String hospitalName;

    @NotEmpty(message = "Enter hospital Address")
    private String hospitalAddress;

    @NotEmpty(message = "Hospital Email Cannot be empty")
    @Email(message = "Email is Invalid")
    @Column(unique = true)
    private String hospitalEmail;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$", message = "Password must contain at least one letter and one digit")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hospital")
    private List<Doctor> doctorList;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Patient> patientList;

}
