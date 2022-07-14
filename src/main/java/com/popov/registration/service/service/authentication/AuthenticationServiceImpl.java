package com.popov.registration.service.service.authentication;

import com.popov.registration.service.entity.authentication.AuthenticationRequestDTO;
import com.popov.registration.service.entity.person.Person;
import com.popov.registration.service.entity.person.dto.PersonDTO;
import com.popov.registration.service.repository.PersonRepository;
import com.popov.registration.service.security.JwtTokenProvider;
import com.popov.registration.service.utils.mappers.person.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> authenticate(AuthenticationRequestDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            Person person = personRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
            String token = jwtTokenProvider.createToken(request.getEmail(), person.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @Override
    public ResponseEntity<?> registration(PersonDTO personDTO) {
        try {
            Person person = PersonMapper.INSTANCE.toPerson(personDTO);
            String token = jwtTokenProvider.createToken(person.getEmail(), person.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", person.getEmail());
            response.put("token", token);
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            try {
                personRepository.save(person);
            } catch (Exception e) {
                return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
            }


            authenticate(new AuthenticationRequestDTO(person.getEmail(), person.getPassword()));
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }
}
