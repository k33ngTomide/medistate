package com.medistate.hospitalAdmin.services;

import com.medistate.dtos.response.HospitalAdminRespond;
import com.medistate.exceptions.AdminExistsException;
import com.medistate.hopsital.data.model.Hospital;
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
    private PasswordEncoder passwordEncoder;
    @Override
    public  HospitalAdminRespond  registerHospitalAdmin(AddHospitalAdminRequest addHospitalAdminRequest, Hospital hospital) {

        validateAdmin(addHospitalAdminRequest.getHospitalAdminEmail());
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

    private void validateAdmin(String HospitalAdmin)  {
        if (hospitalAdminRepository.findByEmail(HospitalAdmin).isPresent()) {
            throw new AdminExistsException("Admin already exists");
        }
    }
    private HospitalAdminRespond registrationMessage(){
        HospitalAdminRespond hospitalAdminRespond = new HospitalAdminRespond();
        hospitalAdminRespond.setStatus("ok");
        hospitalAdminRespond.setMessage("Admin Added Successfully");
//        hospitalAdminRespond.setMessage( "Dear" + addHospitalAdminRequest.getAdminName() + "is now an admin of"
//                + hospital.getHospitalName() +"/n Kindly Check your mail to valid your account");

    }

}
