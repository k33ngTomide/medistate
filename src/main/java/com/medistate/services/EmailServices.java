package com.medistate.services;

import com.medistate.dtos.request.SendMailRequest;
import com.medistate.dtos.response.SendMailResponse;

public interface EmailServices {
    SendMailResponse sendMail(SendMailRequest request);
}
