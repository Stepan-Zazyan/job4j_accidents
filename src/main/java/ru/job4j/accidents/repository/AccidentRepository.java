package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentRepository {

    Collection<Accident> findAll();

    Optional<Accident> findById(int id);

    Accident create(Accident accident);

    boolean update(Accident accident);

}
