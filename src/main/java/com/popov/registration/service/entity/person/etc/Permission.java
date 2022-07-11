package com.popov.registration.service.entity.person.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permission {
    PERSON_READ("persons:read"),
    PERSON_WRITE("persons:write");

    private final String permission;
}
