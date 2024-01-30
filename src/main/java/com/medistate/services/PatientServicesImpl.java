package com.medistate.services;

import com.medistate.data.models.Gender;
import com.medistate.data.models.Hospital;
import com.medistate.data.models.Patient;
import com.medistate.data.repositories.PatientRepository;
import com.medistate.dtos.request.AddPatientRequest;
import com.medistate.dtos.response.AddPatientResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public Patient addPatient(AddPatientRequest addPatientsRequest) {
        Patient patient = modelMapper.map(addPatientsRequest, Patient.class);
        patientRepository.save(patient);
        return patient;
    }
}
