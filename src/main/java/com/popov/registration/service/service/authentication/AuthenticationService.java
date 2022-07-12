package com.popov.registration.service.service.authentication;

import com.popov.registration.service.entity.authentication.AuthenticationRequestDTO;
import com.popov.registration.service.entity.person.dto.PersonDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    ResponseEntity<?> authenticate(AuthenticationRequestDTO request);
    void logout(HttpServletRequest request, HttpServletResponse response);
    ResponseEntity<?> registration(PersonDTO personDTO);

}
