package com.medistate.services;

import com.medistate.data.models.Doctor;
import com.medistate.data.models.Hospital;
import com.medistate.dtos.request.AddDoctorRequest;
import com.medistate.dtos.request.AddPatientRequest;
import com.medistate.dtos.request.RemoveDoctorRequest;
import com.medistate.dtos.response.AddDoctorResponse;
import com.medistate.dtos.response.AddPatientResponse;

public interface DoctorServices {

    Doctor addDoctor(AddDoctorRequest addDoctorRequest, Hospital hospital);

    Doctor findDoctor(String doctorName);

    Doctor deleteDoctor(RemoveDoctorRequest removeDoctorRequest);
}
