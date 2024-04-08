package com.medistate.security;

import com.medistate.hopsital.data.model.Hospital;
import com.medistate.hopsital.data.repositories.HospitalRepository;
import com.medistate.exceptions.HospitalNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@AllArgsConstructor
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
            public UserDetails loadUserByUsername(String hospitalName) throws UsernameNotFoundException {
                Optional<Hospital> foundHospital = hospitalRepository.findHospitalByHospitalName(hospitalName);
                if(foundHospital.isEmpty()) throw  new HospitalNotFoundException("Hospital Not Found");
                Hospital hospital = foundHospital.get();
                System.out.println(hospital.getHospitalName());
                return new SecureUser(hospital);

            }
        };
    }

}
