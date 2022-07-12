package com.popov.registration.service.service.animals;

import com.popov.registration.service.entity.animals.Animal;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AnimalService {
    Optional<Animal> getAnimalById(Long id);
    ResponseEntity<?> saveAnimal(Animal animal);
    ResponseEntity<?> updateAnimal(Animal animal);
    void deleteAnimalById(Long id);
    Optional<List<Animal>> getAllAnimals();
}
