package com.popov.registration.service.controllers;

import com.popov.registration.service.entity.animals.Animal;
import com.popov.registration.service.service.animals.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/animals")
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    @PreAuthorize("hasAuthority('persons:read')")
    public Optional<List<Animal>> getAll() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:read')")
    public Animal getById(@PathVariable Long id) {
        return animalService.getAnimalById(id).get();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:write')")
    public void deleteById(@PathVariable Long id) {
        animalService.deleteAnimalById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('persons:write')")
    public void saveAnimal(@RequestBody Animal animal) {
        animalService.saveAnimal(animal);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:write')")
    public void updateAnimal(@RequestBody Animal animal) {
        animalService.updateAnimal(animal);
    }





}
