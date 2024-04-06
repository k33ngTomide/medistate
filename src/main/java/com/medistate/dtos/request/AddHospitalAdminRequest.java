package com.medistate.dtos.request;

import lombok.Data;

@Data
public class AddHospitalAdminRequest {
    private String adminName;
    private String hospitalAdminEmail;
    private String password;
    private String address;
    private String phoneNumber;

    private String hospitalEmail;
}
