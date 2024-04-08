package com.medistate.services;

import com.medistate.patient.data.models.Patient;
import com.medistate.doctor.data.repositories.DoctorRepository;
import com.medistate.hopsital.data.repositories.HospitalRepository;
import com.medistate.patient.data.repositories.PatientRepository;
import com.medistate.dtos.request.*;
import com.medistate.dtos.response.*;
import com.medistate.hopsital.services.HospitalUserServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class HospitalServicesTest {

    @Autowired
    private HospitalUserServices hospitalServices;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private HospitalRepository hospitalRepository;

    @AfterEach
    public void setUp(){
        doctorRepository.deleteAll();
        hospitalRepository.deleteAll();
        patientRepository.deleteAll();

    }




    @Test
    public void testThatHospitalCanRegister(){
        HospitalRegisterRequest registerRequest = new HospitalRegisterRequest();

        registerRequest.setHospitalName("Asgard Hospital");
        registerRequest.setHospitalAddress("123 Main Sabo Str, Sabo City");
        registerRequest.setHospitalEmail("AsgardHospital@gmail.com");
        registerRequest.setPassword("samplePassword123");

        HospitalRegisterResponse response = hospitalServices.registerHospital(registerRequest);
        assertThat(response, is(notNullValue()));
        assertTrue(response.getStatus().equalsIgnoreCase("Ok"));
    }


    @Test
    @DisplayName("Test That hospital can add doctor")
    @Sql(scripts = {"/db/hospital.sql"})
    public void addDoctorTest(){

        AddDoctorRequest addDoctorRequest = new AddDoctorRequest();
        addDoctorRequest.setDoctorName("Dr. Ajayi Akinkunmi");
        addDoctorRequest.setDoctorEmail("ajayi@example.com");
        addDoctorRequest.setPassword("new1@Password123");
        addDoctorRequest.setAddress("126 sub-Main Street");
        addDoctorRequest.setPhoneNumber("+23490567892");
        addDoctorRequest.setSpecialization("Surgeon");
        addDoctorRequest.setHospitalEmail("AsgardHospital0@gmail.com");
        AddDoctorResponse addDoctorResponse = hospitalServices.addDoctor(addDoctorRequest);
        assertThat(addDoctorResponse.getStatus(), is("ok"));

    }



    @Test
    @DisplayName("Test that doctor can be removed")
    @Sql(scripts = {"/db/hospital.sql"})
    public void testRemoveDoctor(){

        AddDoctorRequest addDoctorRequest = new AddDoctorRequest();
        addDoctorRequest.setDoctorName("Dr. Ajayi Akinkunmi");
        addDoctorRequest.setDoctorEmail("ajayi@example.com");
        addDoctorRequest.setPassword("new1@Password123");
        addDoctorRequest.setAddress("126 sub-Main Street");
        addDoctorRequest.setPhoneNumber("+23490567892");
        addDoctorRequest.setSpecialization("Surgeon");
        addDoctorRequest.setHospitalEmail("AsgardHospital@gmail.com");
        AddDoctorResponse addDoctorResponse = hospitalServices.addDoctor(addDoctorRequest);
        assertThat(addDoctorResponse.getStatus(), is("ok"));

        RemoveDoctorRequest removeDoctorRequest = new RemoveDoctorRequest();
        removeDoctorRequest.setDoctorName("Dr. Ajayi Akinkunmi");
        removeDoctorRequest.setHospitalEmail("AsgardHospital@gmail.com");

        RemoveDoctorResponse removeDoctorResponse = hospitalServices.removeDelete(removeDoctorRequest);
        assertThat(removeDoctorResponse.getStatus(), is("Ok"));

    }

    @Test
    @DisplayName("Test that Hospital can add patients")
    @Sql(scripts = {"/db/hospital.sql"})
    public void testAddPatients(){

        AddPatientRequest addPatientsRequest = new AddPatientRequest();
        addPatientsRequest.setAddress("Sabo Yaba");
        addPatientsRequest.setFullName("Rice Bean");
        addPatientsRequest.setGender("Male");
        addPatientsRequest.setBloodGroup("O+");
        addPatientsRequest.setDateOfBirth("29/09/2024");
        addPatientsRequest.setPhoneNumber("08100232423");
        addPatientsRequest.setHospitalName("Asgard Hospital");

        AddPatientResponse addPatientResponse = hospitalServices.addPatient(addPatientsRequest);
        assertThat(addPatientResponse.getStatus(), is("Ok"));

    }

    @Test
    @DisplayName("Test that hospital can get all patients")
    @Sql(scripts = {"/db/hospital.sql"})
    public void getAllPatients(){
        AddPatientRequest addPatientsRequest = new AddPatientRequest();
        addPatientsRequest.setAddress("Sabo Yaba");
        addPatientsRequest.setFullName("Rice Bean");
        addPatientsRequest.setGender("Male");
        addPatientsRequest.setBloodGroup("O+");
        addPatientsRequest.setDateOfBirth("29/09/2024");
        addPatientsRequest.setPhoneNumber("08100232423");
        addPatientsRequest.setHospitalName("Asgard Hospital");
        AddPatientResponse addPatientResponse = hospitalServices.addPatient(addPatientsRequest);


        AddPatientRequest patientRequest = new AddPatientRequest();
        patientRequest.setAddress("Sabo Yaba");
        patientRequest.setFullName("Agabalagba Oghechi");
        patientRequest.setGender("Female");
        patientRequest.setBloodGroup("B+");
        patientRequest.setDateOfBirth("21/02/2012");
        patientRequest.setPhoneNumber("08100233423");
        patientRequest.setHospitalName("Asgard Hospital");
        AddPatientResponse addPatientResponse1 = hospitalServices.addPatient(patientRequest);

        String hospitalEmail = "AsgardHospital@gmail.com";
        List<Patient> allPatients = hospitalServices.getAllPatient(hospitalEmail);
        assertThat(allPatients.size(), is(2));

    }

}