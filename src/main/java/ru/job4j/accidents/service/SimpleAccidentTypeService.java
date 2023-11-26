package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMem;

import java.util.Collection;

@AllArgsConstructor
@Service
public class SimpleAccidentTypeService implements AccidentTypeService {

    private final AccidentTypeMem accidentTypeMem;
    @Override
    public AccidentType create(AccidentType accidentType) {
        return accidentTypeMem.create(accidentType);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypeMem.findAll();
    }

    @Override
    public AccidentType findById(int id) {
        return accidentTypeMem.findById(id);
    }
}
