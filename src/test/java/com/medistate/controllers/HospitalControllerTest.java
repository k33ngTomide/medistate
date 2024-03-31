package com.medistate.controllers;

import com.medistate.dtos.request.HospitalRegisterRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HospitalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Test for hospital register endpoint with valid credentials")
    public void testRegister() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HospitalRegisterRequest registerRequest = new HospitalRegisterRequest();
        registerRequest.setPassword("1234MuiliyuSodiq");
        registerRequest.setHospitalEmail("test@gmail.com");
        registerRequest.setHospitalAddress("Sabo Yaba");
        registerRequest.setHospitalName("IyaniWura Hospital");
        mockMvc.perform(post("/api/v1/hospital/register")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(registerRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    @DisplayName("Test for hospital login endpoint with a valid credentials")
    public void testLogin() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        HospitalRegisterRequest registerRequest = new HospitalRegisterRequest();
        registerRequest.setPassword("12MuiliyuSodiq");
        registerRequest.setHospitalEmail("testngi@gmail.com");
        registerRequest.setHospitalAddress("Sabo Yaba");
        registerRequest.setHospitalName("Anikulapo Hospital");
        mockMvc.perform(post("/api/v1/hospital/register")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(registerRequest)))
                .andExpect(status().is2xxSuccessful());

        ResultActions result = mockMvc.perform(
                post("/api/v1/hospital/login")
                .contentType(APPLICATION_JSON)
                .content("{\"username\":\"Anikulapo Hospital\", \"password\":\"12MuiliyuSodiq\"}"));

        result.andExpect(MockMvcResultMatchers.status().isOk());
        String token = result.andReturn().getResponse().getContentAsString();
        System.out.println(token);

        assert !token.isEmpty();
    }

}