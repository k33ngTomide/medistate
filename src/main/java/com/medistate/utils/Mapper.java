package com.medistate.utils;

import com.medistate.data.models.Doctor;
import com.medistate.data.models.Hospital;
import com.medistate.dtos.request.AddDoctorRequest;
import com.medistate.dtos.response.AddDoctorResponse;

import java.util.List;

public class Mapper {


    public static Doctor map(AddDoctorRequest addDoctorRequest, Hospital hospital) {
        Doctor doctor = new Doctor();
        doctor.setName(addDoctorRequest.getDoctorName());
        doctor.setAddress(addDoctorRequest.getAddress());
        doctor.setEmail(addDoctorRequest.getDoctorEmail());
        doctor.setSpecialization(addDoctorRequest.getSpecialization());
        doctor.setPhoneNumber(addDoctorRequest.getPhoneNumber());
        doctor.setHospital(hospital);
        return doctor;
    }

    public static AddDoctorResponse map(List<Doctor> doctors) {
        AddDoctorResponse addDoctorResponse = new AddDoctorResponse();
        addDoctorResponse.setMessage("Doctor Added Successfully");
        addDoctorResponse.setStatus("ok");
        addDoctorResponse.setTotalNumberOfDoctors(doctors.size() + "");
        return addDoctorResponse;
    }
}
