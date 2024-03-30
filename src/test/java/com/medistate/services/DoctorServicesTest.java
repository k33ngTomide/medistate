package com.medistate.services;

import com.medistate.doctor.services.DoctorServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class DoctorServicesTest {

    @Autowired
    private DoctorServices doctorServices;




    @Test
    @DisplayName("Test that doctor can get patient")
    public void testAddPatientMedicalHospital(){


    }

}