package com.popov.registration.service.controllers;

import com.popov.registration.service.entity.developer.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class PeopleController {
    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "AMOGUS", "root"),
            new Developer(2L, "GERALD", "root"),
            new Developer(3L, "ISAAC", "root")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Developer> getAll() {
        return DEVELOPERS;
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:read')")
    public Developer getById(@PathVariable Long id) {
        return DEVELOPERS.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('persons:write')")
    public Developer createPerson(@RequestBody Developer developer) {
        this.DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:write')")
    public void deleteById(@PathVariable Long id) {
        this.DEVELOPERS.removeIf(person -> person.getId().equals(id));
    }
}
