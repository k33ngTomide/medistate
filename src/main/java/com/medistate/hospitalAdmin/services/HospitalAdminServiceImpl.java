package com.medistate.hospitalAdmin.services;

import com.medistate.dtos.response.HospitalAdminRespond;
import com.medistate.exceptions.AdminExistsException;
import com.medistate.exceptions.HospitalNotFoundException;
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
    public  HospitalAdminRespond  registerHospitalAdmin(AddHospitalAdminRequest addHospitalAdminRequest, Hospital hospital) {

        validateHospitalName(hospital.getHospitalName());
        validateAdminName(addHospitalAdminRequest.getHospitalAdminEmail());
        HospitalAdmin hospitalAdmin = map(addHospitalAdminRequest, hospital);
        String hashPassword = passwordEncoder.encode(addHospitalAdminRequest.getPassword());
        hospitalAdmin.setPassword(hashPassword);


        hospitalAdminRepository.save(hospitalAdmin);

        return registrationMessage();

    }

    @Override
    public HospitalAdmin registerPatient(AddPatientRequest addPatientRequest) {
        return null;
    }

    private void validateAdminName(String HospitalAdmin)  {
        if (hospitalAdminRepository.findByEmail(HospitalAdmin).isPresent() ) {
            throw new AdminExistsException("Admin already exists");
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
//        hospitalAdminRespond.setMessage( "Dear" + addHospitalAdminRequest.getAdminName() + "is now an admin of"
//                + hospital.getHospitalName() +"/n Kindly Check your mail to valid your account");
        return  hospitalAdminRespond;
    }

}
