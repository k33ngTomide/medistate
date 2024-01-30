package com.medistate.services;

import com.medistate.data.models.Doctor;
import com.medistate.data.models.Hospital;
import com.medistate.data.models.Patient;
import com.medistate.data.repositories.DoctorRepository;
import com.medistate.dtos.request.AddDoctorRequest;
import com.medistate.dtos.request.AddPatientRequest;
import com.medistate.dtos.request.RemoveDoctorRequest;
import com.medistate.dtos.response.AddDoctorResponse;
import com.medistate.dtos.response.AddPatientResponse;
import com.medistate.exceptions.DoctorNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.medistate.utils.Mapper.map;

@Service
@AllArgsConstructor
public class DoctorServicesImpl implements DoctorServices{

    private DoctorRepository doctorRepository;
    private PasswordEncoder passwordEncoder;
    private PatientServices patientServices;

    @Override
    public Doctor addDoctor(AddDoctorRequest addDoctorRequest, Hospital hospital) {
        Doctor doctor = map(addDoctorRequest, hospital);
        String hashedPassword = passwordEncoder.encode(addDoctorRequest.getPassword());
        doctor.setPassword(hashedPassword);
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor findDoctor(String doctorName) {
        Optional<Doctor> foundDoctor = doctorRepository.findDoctorByName(doctorName);
        return foundDoctor.orElseThrow(this::throwDoctorNotFoundException);
    }

    @Override
    public Doctor deleteDoctor(RemoveDoctorRequest removeDoctorRequest) {
        Doctor doctor = findDoctor(removeDoctorRequest.getDoctorName());
        doctorRepository.delete(doctor);
        return doctor;
    }

    private RuntimeException throwDoctorNotFoundException() {
        return new DoctorNotFoundException("Doctor Not Found");
    }
}
