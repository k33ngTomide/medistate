//package com.medistate.hospitalAdmin.services;
//
//import com.medistate.dtos.request.AddHospitalAdminRequest;
//import com.medistate.dtos.request.HospitalRegisterRequest;
//import com.medistate.dtos.response.HospitalAdminRespond;
//import com.medistate.dtos.response.HospitalRegisterResponse;
//import com.medistate.exceptions.AdminExistsException;
//import com.medistate.exceptions.HospitalNotFoundException;
//import com.medistate.hopsital.data.model.Hospital;
//import com.medistate.hopsital.data.repositories.HospitalRepository;
//import com.medistate.hopsital.services.HospitalUserServices;
//import com.medistate.hospitalAdmin.data.models.HospitalAdmin;
//import com.medistate.hospitalAdmin.data.repositories.HospitalAdminRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.notNullValue;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class HospitalAdminServiceImplTest {
//
//    @Autowired
//    private HospitalAdminService hospitalAdminService;
//
//    @Autowired
//    private HospitalUserServices hospitalServices;
//
//    @Autowired
//    private HospitalRepository hospitalRepository;
//
//    @Autowired
//    private HospitalAdminRepository hospitalAdminRepository;
//
//    @BeforeEach
//    public void setUp() {
//        hospitalAdminRepository.deleteAll();
//        hospitalRepository.deleteAll();
//    }
//    @Test
//    @DisplayName("Test that hospital admin can register")
//    void registerHospitalAdmin() {
//
//        HospitalRegisterRequest registerRequest = new HospitalRegisterRequest();
//
//        registerRequest.setHospitalName("Vigor Hospital");
//        registerRequest.setHospitalAddress("123 Main Sabo Str, Sabo City");
//        registerRequest.setHospitalEmail("VigorHospital@gmail.com");
//        registerRequest.setPassword("samplePassword123");
//
//        HospitalRegisterResponse response = hospitalServices.registerHospital(registerRequest);
//        assertThat(response, is(notNullValue()));
//        assertTrue(response.getStatus().equalsIgnoreCase("Ok"));
//
//
//        Hospital hospital = hospitalRepository.findByHospitalName(registerRequest.getHospitalName());
//
//        AddHospitalAdminRequest hospitalAdmin = new AddHospitalAdminRequest();
//        hospitalAdmin.setHospitalAdminEmail("joy3455@gmail.com");
//        hospitalAdmin.setAdminName("Joy");
//        hospitalAdmin.setHospitalAdminEmail("VigorHospital@gmail.com");
//        hospitalAdmin.setPassword("testingmaddness12232");
//        hospitalAdmin.setPhoneNumber("08012345678");
//
//
//
//        HospitalAdminRespond responseForAdmin = hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital);
//
//        assertThat(responseForAdmin, is(notNullValue()));
//        assertTrue(responseForAdmin.getStatus().equalsIgnoreCase("Ok"));
//    }
//    @Test
//    @DisplayName("validate  Admin can't create account without an hospital exiting")
//    void validateHospital() {
//
//        AddHospitalAdminRequest hospitalAdmin = new AddHospitalAdminRequest();
//        hospitalAdmin.setHospitalAdminEmail("joy3455@gmail.com");
//        hospitalAdmin.setAdminName("Joy");
//        hospitalAdmin.setPassword("testingmaddness12232");
//        hospitalAdmin.setPhoneNumber("08012345678");
//
//        Hospital hospital = new Hospital();
//
//        assertThrows(HospitalNotFoundException.class,
//                () -> hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital));
//    }
//    @Test
//    @DisplayName("validate the Admin can't create account with the same name")
//    void validateAdminName() {
//
//        HospitalRegisterRequest registerRequest = new HospitalRegisterRequest();
//
//        registerRequest.setHospitalName("Vigor Hospital");
//        registerRequest.setHospitalAddress("123 Main Sabo Str, Sabo City");
//        registerRequest.setHospitalEmail("VigorHospital@gmail.com");
//        registerRequest.setPassword("samplePassword123");
//
//        HospitalRegisterResponse response = hospitalServices.registerHospital(registerRequest);
//        assertThat(response, is(notNullValue()));
//        assertTrue(response.getStatus().equalsIgnoreCase("Ok"));
//
//
//        Hospital hospital = hospitalRepository.findByHospitalName(registerRequest.getHospitalName());
//
//        AddHospitalAdminRequest hospitalAdmin = new AddHospitalAdminRequest();
//        hospitalAdmin.setHospitalAdminEmail("joy3455@gmail.com");
//        hospitalAdmin.setAdminName("Joy");
//        hospitalAdmin.setHospitalAdminEmail("VigorHospital@gmail.com");
//        hospitalAdmin.setPassword("testingmaddness12232");
//        hospitalAdmin.setPhoneNumber("08012345678");
//
//        HospitalAdminRespond responseForAdmin = hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital);
//
//        assertThat(responseForAdmin, is(notNullValue()));
//        assertTrue(responseForAdmin.getStatus().equalsIgnoreCase("Ok"));
//
//
//        AddHospitalAdminRequest secondAdmin = new AddHospitalAdminRequest();
//        secondAdmin.setHospitalAdminEmail("joy3455@gmail.com");
//        secondAdmin.setAdminName("Joy");
//        secondAdmin.setHospitalAdminEmail("VigorHospital@gmail.com");
//        secondAdmin.setPassword("testingmaddness12232");
//        secondAdmin.setPhoneNumber("08012345678");
//
//        assertThrows(AdminExistsException.class,()->hospitalAdminService.registerHospitalAdmin(secondAdmin, hospital));
//
//
//
//
//
//
//    }
//
//}
package com.medistate.hospitalAdmin.services;

import com.medistate.dtos.request.AddHospitalAdminRequest;
import com.medistate.dtos.request.AddPatientRequest;
import com.medistate.dtos.request.HospitalRegisterRequest;
import com.medistate.dtos.request.LoginAdminRequest;
import com.medistate.dtos.response.HospitalAdminRespond;
import com.medistate.dtos.response.HospitalRegisterResponse;
import com.medistate.exceptions.AdminExistException;
import com.medistate.exceptions.HospitalNotFoundException;
import com.medistate.exceptions.InvalidCredentialsException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


    private HospitalRegisterRequest createHospitalRegisterRequest() {
        HospitalRegisterRequest registerRequest = new HospitalRegisterRequest();
        registerRequest.setHospitalName("Vigor Hospital");
        registerRequest.setHospitalAddress("123 Main Sabo Str, Sabo City");
        registerRequest.setHospitalEmail("VigorHospital@gmail.com");
        registerRequest.setPassword("samplePassword123");
        return registerRequest;
    }

    private AddHospitalAdminRequest createHospitalAdminRequest() {
        AddHospitalAdminRequest hospitalAdmin = new AddHospitalAdminRequest();
        hospitalAdmin.setHospitalAdminEmail("joy3455@gmail.com");
        hospitalAdmin.setAdminName("Joy");
        hospitalAdmin.setHospitalAdminEmail("VigorHospital@gmail.com");
        hospitalAdmin.setPassword("testingmaddness12232");
        hospitalAdmin.setPhoneNumber("08012345678");
        return hospitalAdmin;
    }

    @Test
    @DisplayName("Test hospital admin registration")
    void registerHospitalAdmin() {

        HospitalRegisterRequest registerRequest = createHospitalRegisterRequest();
        HospitalRegisterResponse response = hospitalServices.registerHospital(registerRequest);
        assertThat(response, is(notNullValue()));
        assertTrue(response.getStatus().equalsIgnoreCase("Ok"));
        Hospital hospital = hospitalRepository.findByHospitalName(registerRequest.getHospitalName());


        AddHospitalAdminRequest hospitalAdmin = createHospitalAdminRequest();
        HospitalAdminRespond responseForAdmin = hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital);
        assertThat(responseForAdmin, is(notNullValue()));
        assertTrue(responseForAdmin.getStatus().equalsIgnoreCase("Ok"));
    }

    @Test
    @DisplayName("Validate admin creation without an existing hospital")
    void validateHospital() {

        AddHospitalAdminRequest hospitalAdmin = createHospitalAdminRequest();
        Hospital hospital = new Hospital();
        assertThrows(HospitalNotFoundException.class, () -> hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital));
    }

    @Test
    @DisplayName("Validate admin creation with the same name")
    void testToValidateAdminName() {

        HospitalRegisterRequest registerRequest = createHospitalRegisterRequest();
        HospitalRegisterResponse response = hospitalServices.registerHospital(registerRequest);
        assertThat(response, is(notNullValue()));
        assertTrue(response.getStatus().equalsIgnoreCase("Ok"));
        Hospital hospital = hospitalRepository.findByHospitalName(registerRequest.getHospitalName());


        AddHospitalAdminRequest hospitalAdmin = createHospitalAdminRequest();
        HospitalAdminRespond responseForAdmin = hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital);
        assertThat(responseForAdmin, is(notNullValue()));
        assertTrue(responseForAdmin.getStatus().equalsIgnoreCase("Ok"));


        AddHospitalAdminRequest secondAdmin = createHospitalAdminRequest();
        assertThrows(AdminExistException.class, () -> hospitalAdminService.registerHospitalAdmin(secondAdmin, hospital));
    }

    @Test
    @DisplayName("Admin can login with correct credentials")
    void testAdminWithCorrectCredentialsCanLogin() {
        HospitalRegisterRequest registerRequest = createHospitalRegisterRequest();
        HospitalRegisterResponse response = hospitalServices.registerHospital(registerRequest);
        assertThat(response, is(notNullValue()));
        assertTrue(response.getStatus().equalsIgnoreCase("Ok"));
        Hospital hospital = hospitalRepository.findByHospitalName(registerRequest.getHospitalName());

        AddHospitalAdminRequest hospitalAdmin = createHospitalAdminRequest();
        HospitalAdminRespond responseForAdmin = hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital);
        assertThat(responseForAdmin, is(notNullValue()));
        assertTrue(responseForAdmin.getStatus().equalsIgnoreCase("Ok"));

        LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
        loginAdminRequest.setEmail(hospitalAdmin.getHospitalAdminEmail());
        loginAdminRequest.setPassword(hospitalAdmin.getPassword());

        HospitalAdmin hospitalAdminLogin = hospitalAdminService.loginHospitalAdmin(loginAdminRequest);
        assertTrue(hospitalAdminLogin.getName().equalsIgnoreCase("Joy"));

    }

    @Test
    @DisplayName("Hospital Admin Login with wrong email")
    void testHospitalAdminLoginWithWrongEmail() {
        HospitalRegisterRequest registerRequest = createHospitalRegisterRequest();
        HospitalRegisterResponse response = hospitalServices.registerHospital(registerRequest);
        assertThat(response, is(notNullValue()));
        assertTrue(response.getStatus().equalsIgnoreCase("Ok"));
        Hospital hospital = hospitalRepository.findByHospitalName(registerRequest.getHospitalName());

        AddHospitalAdminRequest hospitalAdmin = createHospitalAdminRequest();
        HospitalAdminRespond responseForAdmin = hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital);
        assertThat(responseForAdmin, is(notNullValue()));
        assertTrue(responseForAdmin.getStatus().equalsIgnoreCase("Ok"));

        LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
        loginAdminRequest.setEmail("john@example.com");
        loginAdminRequest.setPassword(hospitalAdmin.getPassword());


        assertThrows(InvalidCredentialsException.class, () -> hospitalAdminService.loginHospitalAdmin(loginAdminRequest));


    }


    @Test
    @DisplayName("Hospital Admin Login with wrong credentials")
    void testAdminWithWrongCredentialsCanLogin() {
        HospitalRegisterRequest registerRequest = createHospitalRegisterRequest();
        HospitalRegisterResponse response = hospitalServices.registerHospital(registerRequest);
        assertThat(response, is(notNullValue()));
        assertTrue(response.getStatus().equalsIgnoreCase("Ok"));
        Hospital hospital = hospitalRepository.findByHospitalName(registerRequest.getHospitalName());

        AddHospitalAdminRequest hospitalAdmin = createHospitalAdminRequest();
        HospitalAdminRespond responseForAdmin = hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital);
        assertThat(responseForAdmin, is(notNullValue()));
        assertTrue(responseForAdmin.getStatus().equalsIgnoreCase("Ok"));

        LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
        loginAdminRequest.setEmail(hospitalAdmin.getHospitalAdminEmail());
        loginAdminRequest.setPassword("wrongPassword");



        assertThrows(InvalidCredentialsException.class, () -> hospitalAdminService.loginHospitalAdmin(loginAdminRequest));
    }

//    @Test
//    @DisplayName("Hospital Admin can create accounts for patient")
//    void testHospitalAdminCanCreateAccountForPatient() {
//        HospitalRegisterRequest registerRequest = createHospitalRegisterRequest();
//        HospitalRegisterResponse response = hospitalServices.registerHospital(registerRequest);
//        assertThat(response, is(notNullValue()));
//        assertTrue(response.getStatus().equalsIgnoreCase("Ok"));
//        Hospital hospital = hospitalRepository.findByHospitalName(registerRequest.getHospitalName());
//
//        AddHospitalAdminRequest hospitalAdmin = createHospitalAdminRequest();
//        HospitalAdminRespond responseForAdmin = hospitalAdminService.registerHospitalAdmin(hospitalAdmin, hospital);
//        assertThat(responseForAdmin, is(notNullValue()));
//        assertTrue(responseForAdmin.getStatus().equalsIgnoreCase("Ok"));
//
//        AddPatientRequest addPatientRequest = new AddPatientRequest();
//        addPatientRequest.setEmail("gracejaames@gmail.com");
//        addPatientRequest.setFullName("Grace James");
//        addPatientRequest.setBloodGroup("");
//        addPatientRequest.setGender("Female");
//        addPatientRequest.setHospitalName("Vigor Hospital");
//
//
//        hospitalAdminService.registerPatient(addPatientRequest);
//
//
//
//
//
//
//    }


}
