package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;

public interface AccidentTypeRepository {

    AccidentType create(AccidentType accidentType);

    Collection<AccidentType> findAll();

    AccidentType findById(int id);
}
