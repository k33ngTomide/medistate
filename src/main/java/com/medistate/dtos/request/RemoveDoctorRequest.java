package com.medistate.dtos.request;

import lombok.Data;

@Data
public class RemoveDoctorRequest {

    private String doctorName;
    private String hospitalEmail;
}
