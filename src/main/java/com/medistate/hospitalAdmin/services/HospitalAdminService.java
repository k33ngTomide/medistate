package com.medistate.hospitalAdmin.services;

import com.medistate.dtos.request.LoginAdminRequest;
import com.medistate.dtos.response.AddPatientResponse;
import com.medistate.dtos.response.HospitalAdminRespond;
import com.medistate.hopsital.data.model.Hospital;
import com.medistate.hospitalAdmin.data.models.HospitalAdmin;
import com.medistate.dtos.request.AddHospitalAdminRequest;
import com.medistate.dtos.request.AddPatientRequest;
import lombok.AllArgsConstructor;

public interface HospitalAdminService {

 HospitalAdminRespond registerHospitalAdmin(AddHospitalAdminRequest addHospitalAdminRequest, Hospital hospital);

 HospitalAdmin loginHospitalAdmin(LoginAdminRequest loginAdminRequest);

 AddPatientResponse  registerPatient (AddPatientRequest addPatientRequest);





}
