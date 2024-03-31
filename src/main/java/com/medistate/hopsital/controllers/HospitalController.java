package com.medistate.hopsital.controllers;

import com.medistate.dtos.request.HospitalRegisterRequest;
import com.medistate.dtos.response.HospitalRegisterResponse;
<<<<<<< HEAD:src/main/java/com/medistate/hopsital/controllers/HospitalController.java
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

=======
import com.medistate.services.HospitalServices;
import jakarta.ws.rs.GET;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

>>>>>>> eb55644d56b846b98b432bea0e61b8adf10be174:src/main/java/com/medistate/controllers/HospitalController.java
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/hospital")
public class HospitalController {

    private HospitalServices hospitalServices;

    @PostMapping("/register")
    public ResponseEntity<HospitalRegisterResponse> register(@RequestBody HospitalRegisterRequest registerRequest){
        return ResponseEntity.status(CREATED).body(hospitalServices.registerHospital(registerRequest));
    }




    @GetMapping("/google/register")
    public Map<String, Object> registerWith(OAuth2AuthenticationToken auth2AuthenticationToken){
        return auth2AuthenticationToken.getPrincipal().getAttributes();
    }

}
