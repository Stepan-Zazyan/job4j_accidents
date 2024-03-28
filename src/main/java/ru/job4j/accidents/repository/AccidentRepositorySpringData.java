package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accidents.model.Accident;

import java.util.Optional;

public interface AccidentRepositorySpringData extends JpaRepository<Accident, Integer> {
    Optional<Accident> findById(int id);
}
