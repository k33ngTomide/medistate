package com.medistate.dtos.request;

import lombok.Data;

@Data
public class AddDoctorRequest {

    private String doctorName;
    private String doctorEmail;
    private String password;
    private String address;
    private String phoneNumber;
    private String specialization;
    private String hospitalEmail;


}
