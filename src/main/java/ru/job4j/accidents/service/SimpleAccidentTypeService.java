package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepositorySpringData;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleAccidentTypeService implements AccidentTypeService {

    private final AccidentTypeRepositorySpringData accidentTypeMem;
    @Override
    public AccidentType create(AccidentType accidentType) {
        return accidentTypeMem.save(accidentType);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypeMem.findAll();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return accidentTypeMem.findById(id);
    }
}
