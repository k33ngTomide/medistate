package com.medistate.services;

import com.medistate.data.models.Doctor;
import com.medistate.data.models.Hospital;
import com.medistate.data.models.Patient;
import com.medistate.data.repositories.HospitalRepository;
import com.medistate.dtos.request.*;
import com.medistate.dtos.response.*;
import com.medistate.exceptions.HospitalNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.medistate.utils.Mapper.map;

@Service
@AllArgsConstructor
public class HospitalServices implements HospitalUserServices {

    private ModelMapper mapper;
    private HospitalRepository hospitalRepository;
    private DoctorServices doctorServices;
    private PatientServices patientServices;

    @Override
    public HospitalRegisterResponse registerHospital(HospitalRegisterRequest registerRequest) {
        Hospital hospital = mapper.map(registerRequest, Hospital.class);
        hospitalRepository.save(hospital);

        HospitalRegisterResponse response = new HospitalRegisterResponse();
        response.setMessage("Hospital Registered Successfully");
        response.setStatus("ok");
        return response;

    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    @Transactional
    public AddDoctorResponse addDoctor(AddDoctorRequest addDoctorRequest) {
        Hospital hospital = findHospital(addDoctorRequest.getHospitalEmail());

        Doctor doctor = doctorServices.addDoctor(addDoctorRequest, hospital);
        List<Doctor> doctors = hospital.getDoctorList();
        doctors.add(doctor);
        System.out.println(Arrays.toString(doctors.toArray()));
        hospital.setDoctorList(doctors);
        hospitalRepository.saveAndFlush(hospital);
        return map(doctors);
    }

    @Override
    public List<Patient> getAllPatient(String hospitalEmail) {
        Hospital hospital = findHospital(hospitalEmail);
        return hospital.getPatientList();

    }

    @Override
    @Transactional
    public AddPatientResponse addPatient(AddPatientRequest addPatientsRequest) {
        Hospital hospital = findHospital(addPatientsRequest.getHospitalName());
        Patient patient = patientServices.addPatient(addPatientsRequest);

        List<Patient> patients = hospital.getPatientList();
        patients.add(patient);
        hospital.setPatientList(patients);
        hospitalRepository.saveAndFlush(hospital);

        AddPatientResponse addPatientResponse = new AddPatientResponse();
        addPatientResponse.setMessage("Patient Added Successfully");
        addPatientResponse.setStatus("Ok");
        return addPatientResponse;
    }

    @Override
    public RemoveDoctorResponse removeDelete(RemoveDoctorRequest removeDoctorRequest) {

        Doctor doctor = doctorServices.deleteDoctor(removeDoctorRequest);
        Hospital hospital = findHospital(removeDoctorRequest.getHospitalEmail());
        List<Doctor> doctors  = hospital.getDoctorList();
        doctors.remove(doctor);


        RemoveDoctorResponse response = new RemoveDoctorResponse();
        response.setMessage("Doctor Details Deleted Successfully");
        response.setStatus("Ok");

        return response;

    }

    public Hospital findHospital(String hospitalDetail) {
        Optional<Hospital> foundHospital;
        if(hospitalDetail.contains("@")){
            foundHospital = hospitalRepository.findHospitalByHospitalEmail(hospitalDetail);
        } else {
            foundHospital = hospitalRepository.findHospitalByHospitalName(hospitalDetail);
        }
        if(foundHospital.isEmpty()){
            throw new HospitalNotFoundException("Hospital Not Found");
        }
        return foundHospital.get();
    }
}
