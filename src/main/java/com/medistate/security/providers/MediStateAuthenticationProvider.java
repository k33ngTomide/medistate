package com.medistate.security.providers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

;


@Component
@AllArgsConstructor
@Slf4j
public class MediStateAuthenticationProvider implements AuthenticationProvider {


    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        System.out.println(username);

        String password = authentication.getCredentials().toString();
        System.out.println(password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println(userDetails.getUsername());
        boolean isValidPasswordMatch = passwordEncoder.matches(password, userDetails.getPassword());
        System.out.println(isValidPasswordMatch);
        if (isValidPasswordMatch){
            return new UsernamePasswordAuthenticationToken(username, password,
                    userDetails.getAuthorities());
        }
        throw new BadCredentialsException("Incorrect credentials, username or password incorrect");


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
