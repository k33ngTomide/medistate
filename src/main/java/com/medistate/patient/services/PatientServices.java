package com.medistate.patient.services;

import com.medistate.dtos.request.*;
import com.medistate.hopsital.data.model.Hospital;
import com.medistate.patient.data.models.Patient;

import java.util.List;

public interface PatientServices {

    List<Patient> getAllPatients(Hospital hospital);

    void validatePatient(String patientFullName);

    Patient addPatient(AddPatientRequest addPatientsRequest);

    Patient removePatient(RemovePatientRequest removePatientsRequest);

    Patient updateInfoOfPatient(UpdateInfoRequest updateInfoRequest);

    Patient viewPrescription(ViewPrescriptionRequest viewPrescriptionRequest);

    Patient viewDoctorAppointmentDate(ViewDoctorAppointmentDateRequest viewDoctorAppointmentDateRequest);




}
