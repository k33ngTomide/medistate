package com.medistate.dtos.request;

import jakarta.jms.JMSPasswordCredential;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginAdminRequest {
    @Column(unique = true)
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$", message = "Password must contain at least one letter and one digit")
    String password;
}
