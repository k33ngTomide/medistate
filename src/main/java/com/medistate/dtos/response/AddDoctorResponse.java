package com.medistate.dtos.response;

import lombok.Data;

@Data
public class AddDoctorResponse {

    private String status;
    private String message;
    private String totalNumberOfDoctors;

}
