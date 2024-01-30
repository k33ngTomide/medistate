package com.medistate.services;

import com.medistate.data.models.Hospital;
import com.medistate.data.models.Patient;
import com.medistate.dtos.request.AddPatientRequest;
import com.medistate.dtos.response.AddPatientResponse;

import java.util.List;

public interface PatientServices {

    List<Patient> getAllPatients(Hospital hospital);

    Patient addPatient(AddPatientRequest addPatientsRequest);
}
