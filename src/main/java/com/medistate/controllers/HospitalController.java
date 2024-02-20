package com.medistate.controllers;

import com.medistate.dtos.request.HospitalRegisterRequest;
import com.medistate.dtos.response.HospitalRegisterResponse;
import com.medistate.services.HospitalServices;
import jakarta.ws.rs.GET;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
