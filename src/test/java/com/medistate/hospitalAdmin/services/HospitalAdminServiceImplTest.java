package com.medistate.hospitalAdmin.services;

import com.medistate.dtos.request.AddHospitalAdminRequest;
import com.medistate.dtos.request.HospitalRegisterRequest;
import com.medistate.dtos.response.HospitalAdminRespond;
import com.medistate.dtos.response.HospitalRegisterResponse;
import com.medistate.exceptions.HospitalNotFoundException;
import com.medistate.hopsital.data.model.Hospital;
import com.medistate.hopsital.data.repositories.HospitalRepository;
import com.medistate.hopsital.services.HospitalUserServices;
import com.medistate.hospitalAdmin.data.models.HospitalAdmin;
import com.medistate.hospitalAdmin.data.repositories.HospitalAdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class HospitalAdminServiceImplTest {

    @Autowired
    private HospitalAdminService hospitalAdminService;

    @Autowired
    private HospitalUserServices hospitalServices;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalAdminRepository hospitalAdminRepository;

    @BeforeEach
    public void setUp() {
        hospitalAdminRepository.deleteAll();
        hospitalRepository.deleteAll();
    }
    @Test
    @DisplayName("Test that hospital admin can register")
    void registerHospitalAdmin() {
        HospitalRegisterRequest registerRequest = new HospitalRegisterRequest();

        registerRequest.setHospitalName("Vigor Hospital");
        registerRequest.setHospitalAddress("123 Main Sabo Str, Sabo City");
        registerRequest.setHospitalEmail("VigorHospital@gmail.com");
        registerRequest.setPassword("samplePassword123");

        HospitalRegisterResponse response = hospitalServices.registerHospital(registerRequest);
        assertThat(response, is(notNullValue()));
        assertTrue(response.getStatus().equalsIgnoreCase("Ok"));


        Hospital hospital = hospitalRepository.findByHospitalName(registerRequest.getHospitalName());

        AddHospitalAdminRequest hospitalAdmin = new AddHospitalAdminRequest();
        hospitalAdmin.setHospitalAdminEmail("joy3455@gmail.com");
        hospitalAdmin.setAdminName("Joy");
        hospitalAdmin.setHospitalAdminEmail("VigorHospital@gmail.com");
        hospitalAdmin.setPassword("testingmaddness12232");
        hospitalAdmin.setPhoneNumber("08012345678");



        HospitalAdminRespond responseForAdmin = hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital);

        assertThat(responseForAdmin, is(notNullValue()));
        assertTrue(responseForAdmin.getStatus().equalsIgnoreCase("Ok"));
    }
    @Test
    @DisplayName("validate the hospital can ")
    void validateHospital() {

        Hospital hospital = new Hospital();
        hospital.setHospitalName("Vigor Hospital");
        hospital.setHospitalAddress("123 Main Sabo Str, Sabo City");
        hospital.setHospitalEmail("VigorHospital@gmail.com");
        hospital.setPassword("samplePassword123");


        hospitalRepository.save(hospital);


        AddHospitalAdminRequest hospitalAdmin = new AddHospitalAdminRequest();
        hospitalAdmin.setHospitalAdminEmail("joy3455@gmail.com");
        hospitalAdmin.setAdminName("Joy");
        hospitalAdmin.setPassword("testingmaddness12232");
        hospitalAdmin.setPhoneNumber("08012345678");



        assertDoesNotThrow(() -> hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital));
    }


}
