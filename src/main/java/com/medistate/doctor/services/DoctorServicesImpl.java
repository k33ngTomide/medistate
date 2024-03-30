package com.medistate.doctor.services;

import com.medistate.doctor.data.models.Doctor;
import com.medistate.hopsital.data.model.Hospital;
import com.medistate.patient.data.models.Patient;
import com.medistate.doctor.data.repositories.DoctorRepository;
import com.medistate.dtos.request.*;
import com.medistate.exceptions.DoctorNotFoundException;
import com.medistate.patient.services.PatientServices;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public Boolean verifyToken(String token) {
        return null;
    }

    @Override
    public Patient addPatient(AddMedicalRecordRequest addPatientRecordRequest) {
        return null;
    }

    @Override
    public Patient viewMedicalRecord(ViewMedicalRecordRequest viewMedicalRecordRequest) {
        return null;
    }

    @Override
    public Patient viewPatient(ViewPatientsRequest viewPatientRequest) {
        return null;
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
    private  Patient getPatient(String patientName){
        return null;

    }
}
