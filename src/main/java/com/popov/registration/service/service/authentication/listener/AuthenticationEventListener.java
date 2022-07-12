package com.popov.registration.service.service.authentication.listener;

import com.popov.registration.service.entity.person.Person;
import com.popov.registration.service.entity.person.etc.Logins;
import com.popov.registration.service.error.entity.EntityNotFoundException;
import com.popov.registration.service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationEventListener {

    private final PersonRepository personRepository;

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
        Person person = personRepository.findByEmail((String) event.getAuthentication().getPrincipal())
                .orElseThrow(() -> new EntityNotFoundException(Person.class, "id", "not found"));


        person.setLogins(new Logins(event.getTimestamp()));
        personRepository.save(person);

    }

}
