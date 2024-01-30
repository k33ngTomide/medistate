package com.medistate.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.medistate.data.models.Patient;
import com.medistate.dtos.request.*;
import com.medistate.dtos.response.*;

import java.util.List;

public interface HospitalUserServices {

    HospitalRegisterResponse registerHospital(HospitalRegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    AddDoctorResponse addDoctor(AddDoctorRequest addDoctorRequest);

    List<Patient> getAllPatient(String hospitalEmail);

    AddPatientResponse addPatient(AddPatientRequest addPatientsRequest);

    RemoveDoctorResponse removeDelete(RemoveDoctorRequest removeDoctorRequest);
}
