package com.medistate.dtos.request;

import lombok.Data;

@Data
public class AddHospitalAdminRequest {
    private String AdminName;
    private String HospitalAdminEmail;
    private String password;
    private String address;
    private String phoneNumber;

    private String hospitalEmail;
}
