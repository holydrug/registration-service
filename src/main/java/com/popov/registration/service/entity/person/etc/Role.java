package com.popov.registration.service.entity.person.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Role {
    USER(Set.of(Permission.PERSON_READ)),
    ADMIN(Set.of(Permission.PERSON_READ, Permission.PERSON_WRITE));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorites() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
