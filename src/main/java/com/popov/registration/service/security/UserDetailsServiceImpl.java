package com.popov.registration.service.security;

import com.popov.registration.service.entity.person.Person;
import com.popov.registration.service.entity.person.etc.Status;
import com.popov.registration.service.repository.PersonRepository;
import com.popov.registration.service.service.authentication.attempt.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;
    private final LoginAttemptService loginAttemptService;
    private final HttpServletRequest request;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }

        try {
            Person user = personRepository.findByEmail(email).get();
            if (user == null) {
                return new org.springframework.security.core.userdetails.User(
                        " ", " ", true, true, true, true,
                        user.getRole().getAuthorites());
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), user.getStatus().equals(Status.ACTIVE), true, true, true,
                    user.getRole().getAuthorites());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
