package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Optional;

@Repository
public interface AccidentRepositorySpringData extends JpaRepository<Accident, Integer> {

}
