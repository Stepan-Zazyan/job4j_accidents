package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accidents.model.Accident;

public interface AccidentRepositorySpringData extends JpaRepository<Accident, Integer> {
}
