package com.medistate.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    @Value("${jwt.signing.key}")
    private String jwtSecretKey;
    @Value("${jwt.expiry.days}")
    private String jwtDuration;

    public String getJwtSecret(){
        return jwtSecretKey;
    }
    public int getJwtDuration(){
        return Integer.parseInt(jwtDuration);
    }
}
