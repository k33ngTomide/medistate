package com.medistate.utils;

import com.medistate.doctor.data.models.Doctor;
import com.medistate.dtos.request.AddHospitalAdminRequest;
import com.medistate.hopsital.data.model.Hospital;
import com.medistate.dtos.request.AddDoctorRequest;
import com.medistate.dtos.response.AddDoctorResponse;
import com.medistate.hospitalAdmin.data.models.HospitalAdmin;

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

    public static HospitalAdmin map (AddHospitalAdminRequest addAdminRequest,Hospital hospital) {
        HospitalAdmin hospitalAdmin = new HospitalAdmin();
        hospitalAdmin.setName(addAdminRequest.getAdminName());
        hospitalAdmin.setEmail(addAdminRequest.getHospitalAdminEmail());
        hospitalAdmin.setPhoneNumber(addAdminRequest.getPhoneNumber());
        hospitalAdmin.setHospital(hospital);
        return hospitalAdmin;
    }
}
