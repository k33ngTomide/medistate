package com.medistate.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class HospitalRegisterRequest {

    @NotEmpty(message = "Hospital Name cannot be empty")
    @Size(min = 2, message = "Hospital Name must be more than 1 letter")
    private String hospitalName;

    @NotEmpty(message = "Enter hospital Address")
    private String hospitalAddress;

    @NotEmpty(message = "Hospital Email Cannot be empty")
    @Email(message = "Email is Invalid")
    private String hospitalEmail;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$", message = "Password must contain at least one letter and one digit")
    private String password;

    private List<String> doctorList;

}
