package com.medistate.controllers;

import com.medistate.dtos.request.HospitalRegisterRequest;
import com.medistate.dtos.request.LoginRequest;
import com.medistate.dtos.response.HospitalRegisterResponse;
import com.medistate.security.services.JwtService;
import com.medistate.services.HospitalServices;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/hospital")
public class HospitalController {

    private HospitalServices hospitalServices;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;


    @PostMapping("/register")
    public ResponseEntity<HospitalRegisterResponse> register(@RequestBody HospitalRegisterRequest registerRequest){
        return ResponseEntity.status(CREATED).body(hospitalServices.registerHospital(registerRequest));
    }


}
