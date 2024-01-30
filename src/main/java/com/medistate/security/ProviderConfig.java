package com.medistate.security;

import com.medistate.data.models.Hospital;
import com.medistate.data.repositories.HospitalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class ProviderConfig {
    private HospitalRepository hospitalRepository;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Optional<Hospital> foundHospital = hospitalRepository.findHospitalByHospitalEmail(email);
                Hospital hospital = foundHospital.orElseThrow();
                return new SecureUser(hospital);

            }
        };
    }

}
