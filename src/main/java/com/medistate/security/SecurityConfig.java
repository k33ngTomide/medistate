package com.medistate.security;

import com.medistate.data.models.Hospital;
import com.medistate.data.repositories.HospitalRepository;
import com.medistate.security.filters.JwtAuthorizationFilter;
import com.medistate.security.filters.MedistateAuthenticationFilter;
import com.medistate.security.services.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        try {
            var authenticationFilter = new MedistateAuthenticationFilter(jwtService, authenticationManager);
            authenticationFilter.setFilterProcessesUrl("/api/v1/hospital/login");
            return http.csrf(AbstractHttpConfigurer::disable)
                    .cors(Customizer.withDefaults())
                    .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new JwtAuthorizationFilter(jwtService), authenticationFilter.getClass())
                    .authorizeHttpRequests(c -> c.requestMatchers(POST, "/api/v1/hospital/login", "/api/v1/hospital/register")
                            .permitAll()
                            .anyRequest().authenticated()).build();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
