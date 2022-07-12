package com.popov.registration.service.repository;

import com.popov.registration.service.entity.animals.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
