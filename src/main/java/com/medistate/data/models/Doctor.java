package com.medistate.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Doctor name cannot be empty")
    private String name;

    @Column(unique = true)
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$", message = "Password must contain at least one letter and one digit")
    private String password;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotEmpty(message = "specialization cannot be empty")
    private String specialization;

    @ManyToOne
    private Hospital hospital;
}
