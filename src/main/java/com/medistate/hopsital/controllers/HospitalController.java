package com.medistate.hopsital.controllers;

import com.medistate.dtos.request.HospitalRegisterRequest;
import com.medistate.dtos.response.HospitalRegisterResponse;
import com.medistate.security.services.JwtService;
import com.medistate.hopsital.services.HospitalServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
