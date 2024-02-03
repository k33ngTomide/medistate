package com.medistate.security.filters;

import com.medistate.dtos.request.LoginRequest;
import com.medistate.security.services.JwtService;
import jakarta.servlet.FilterChain;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static com.medistate.utils.AppUtils.ACCESS_TOKEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class MedistateAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private  final JwtService jwtService;
    private ObjectMapper mapper = new ObjectMapper();
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                            HttpServletResponse response) throws AuthenticationException {
        System.out.println("i got to the Auth Manager");

        try(InputStream stream  = request.getInputStream()){
            System.out.println("Inputstream place");
            LoginRequest loginRequest = mapper.readValue(stream, LoginRequest.class);
            System.out.println("So where in the world");
            String hospitalName = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            System.out.println("There it happened");
            Authentication authentication = new UsernamePasswordAuthenticationToken(hospitalName, password);
            System.out.println("i was given an AUTH to work with");

            Authentication authenticationResult = authenticationManager.authenticate(authentication);
            System.out.println("i got to the Auth Manager and Auth xxfull");


            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authenticationResult);

            return authenticationResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected  void successfulAuthentication(HttpServletRequest request,
                                             HttpServletResponse response,
                                             FilterChain filterChain,
                                             Authentication authenticationResult) throws IOException {
        String token = jwtService.generateTokenFor(authenticationResult.getPrincipal().toString());

        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(mapper.writeValueAsBytes(Map.of(ACCESS_TOKEN, token)));
        response.flushBuffer();
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().flush();
    }
}
