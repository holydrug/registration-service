package com.popov.registration.service.service.animals;

import com.popov.registration.service.entity.animals.Animal;
import com.popov.registration.service.error.entity.EntityNotFoundException;
import com.popov.registration.service.repository.AnimalRepository;
import com.popov.registration.service.utils.mappers.animal.AnimalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;


    @Override
    public Optional<Animal> getAnimalById(Long id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Animal.class, "id", id.toString()));
        return Optional.of(animal);
    }

    @Override
    public ResponseEntity<?> saveAnimal(Animal animal) {
        animalRepository.save(animal);
        return ResponseEntity.ok("SUCCESS SAVED\n" + animal);
    }

    @Override
    public ResponseEntity<?> updateAnimal(Animal animalDto) {
        Animal animal = new Animal();
        AnimalMapper.INSTANCE.updateAnimalFromDto(animalDto, animal);
        animalRepository.save(animal);
        return ResponseEntity.ok("SUCCESS UPDATED\n" + animal);
    }

    @Override
    public void deleteAnimalById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Animal.class, "id", id.toString()));
        animalRepository.deleteById(id);
    }

    @Override
    public Optional<List<Animal>> getAllAnimals() {
        return Optional.of(animalRepository.findAll());
    }
}
