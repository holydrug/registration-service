package com.popov.registration.service.controllers;

import com.popov.registration.service.entity.authentication.AuthenticationRequestDTO;
import com.popov.registration.service.entity.person.dto.PersonDTO;
import com.popov.registration.service.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

private final AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody PersonDTO personDTO) {
        return authenticationService.registration(personDTO);
    }

}
