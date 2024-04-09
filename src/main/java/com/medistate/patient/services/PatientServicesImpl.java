package com.medistate.patient.services;

import com.medistate.dtos.request.*;
import com.medistate.exceptions.PatientExistException;
import com.medistate.hopsital.data.model.Hospital;
import com.medistate.patient.data.models.Patient;
import com.medistate.patient.data.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientServicesImpl implements PatientServices {

    private PatientRepository patientRepository;
    private ModelMapper modelMapper;


    @Override
    public List<Patient> getAllPatients(Hospital hospital) {
        return null;
    }

    @Override
    public void validatePatient(String patientFullName) {
        Optional<Patient> patient = patientRepository.findByFullName(patientFullName);
        if (patient.isPresent()) {
            throw new PatientExistException("Patient already exists");
        }
    }
    @Override
    public Patient addPatient(AddPatientRequest addPatientsRequest) {
        Patient patient = modelMapper.map(addPatientsRequest, Patient.class);
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient removePatient(RemovePatientRequest removePatientsRequest) {
        return null;
    }

    @Override
    public Patient updateInfoOfPatient(UpdateInfoRequest updateInfoRequest) {
        return null;
    }

    @Override
    public Patient viewPrescription(ViewPrescriptionRequest viewPrescriptionRequest) {
        return null;
    }

    @Override
    public Patient viewDoctorAppointmentDate(ViewDoctorAppointmentDateRequest viewDoctorAppointmentDateRequest) {
        return null;
    }


}
