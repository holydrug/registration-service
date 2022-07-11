package com.popov.registration.service.entity.person.dto;

import com.popov.registration.service.entity.person.etc.Role;
import com.popov.registration.service.entity.person.etc.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private Role role;
    private Status status;

    public PersonDTO(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
