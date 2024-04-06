package com.medistate.hospitalAdmin.services;

import com.medistate.dtos.request.LoginAdminRequest;
import com.medistate.dtos.response.AddPatientResponse;
import com.medistate.dtos.response.HospitalAdminRespond;
import com.medistate.exceptions.AdminExistException;
import com.medistate.exceptions.HospitalNotFoundException;
import com.medistate.exceptions.InvalidCredentialsException;
import com.medistate.hopsital.data.model.Hospital;
import com.medistate.hopsital.data.repositories.HospitalRepository;
import com.medistate.hospitalAdmin.data.models.HospitalAdmin;
import com.medistate.dtos.request.AddHospitalAdminRequest;
import com.medistate.dtos.request.AddPatientRequest;
import com.medistate.hospitalAdmin.data.repositories.HospitalAdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.medistate.utils.Mapper.map;

@Service
@AllArgsConstructor
public class HospitalAdminServiceImpl  implements HospitalAdminService{

    private HospitalAdminRepository hospitalAdminRepository;

    private HospitalRepository hospitalRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public  HospitalAdminRespond  registerHospitalAdmin
            (AddHospitalAdminRequest addHospitalAdminRequest, Hospital hospital) {

        validateHospitalName(hospital.getHospitalName());
        validateAdminName(addHospitalAdminRequest.getHospitalAdminEmail());

        HospitalAdmin hospitalAdmin = map(addHospitalAdminRequest, hospital);

        String hashPassword = passwordEncoder.encode(addHospitalAdminRequest.getPassword());
        hospitalAdmin.setPassword(hashPassword);

        hospitalAdminRepository.save(hospitalAdmin);

        return registrationMessage();
    }

    @Override
    public HospitalAdmin loginHospitalAdmin(LoginAdminRequest loginAdminRequest) {

        HospitalAdmin hospitalAdmin = hospitalAdminRepository.findByEmail(loginAdminRequest.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(loginAdminRequest.getPassword(), hospitalAdmin.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        return hospitalAdmin;


    }



    @Override
    public AddPatientResponse registerPatient(AddPatientRequest addPatientRequest) {
        return null;
    }


    private void validateAdminName(String HospitalAdmin)  {
        if (hospitalAdminRepository.findByEmail(HospitalAdmin).isPresent() ) {
            throw new AdminExistException("Admin already exists");
        }
    }

    private void validateHospitalName(String hospitalName) {
     if (hospitalRepository.findHospitalByHospitalName(hospitalName).isEmpty()) {
         throw new HospitalNotFoundException("Hospital not found");
     }
    }

    private HospitalAdminRespond registrationMessage(){
        HospitalAdminRespond hospitalAdminRespond = new HospitalAdminRespond();
        hospitalAdminRespond.setStatus("OK");
        hospitalAdminRespond.setMessage("Admin Added Successfully");

//        hospitalAdminRespond.setMessage(String.format("Dear %s is now an admin of %s. Kindly check your email to validate your account.",
//                addHospitalAdminRequest.getAdminName(), hospital.getHospitalName()));
        return  hospitalAdminRespond;
    }

}
