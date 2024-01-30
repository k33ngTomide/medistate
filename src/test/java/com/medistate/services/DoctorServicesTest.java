package com.medistate.services;

import com.medistate.dtos.request.AddPatientRequest;
import com.medistate.dtos.response.AddPatientResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DoctorServicesTest {

    @Autowired
    private DoctorServices doctorServices;




    @Test
    @DisplayName("Test that doctor can get patient")
    public void testAddPatientMedicalHospital(){


    }

}