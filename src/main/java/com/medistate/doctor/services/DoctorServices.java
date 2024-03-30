package com.medistate.doctor.services;

import com.medistate.doctor.data.models.Doctor;
import com.medistate.hopsital.data.model.Hospital;
import com.medistate.patient.data.models.Patient;
import com.medistate.dtos.request.*;


public interface DoctorServices {

    Doctor addDoctor(AddDoctorRequest addDoctorRequest, Hospital hospital);

    Boolean verifyToken(String token);

    Patient addPatient(AddMedicalRecordRequest addPatientRecordRequest);

    Patient  viewMedicalRecord(ViewMedicalRecordRequest viewMedicalRecordRequest);

    Patient viewPatient(ViewPatientsRequest viewPatientRequest);

    Doctor findDoctor(String doctorName);

    Doctor deleteDoctor(RemoveDoctorRequest removeDoctorRequest);
}
