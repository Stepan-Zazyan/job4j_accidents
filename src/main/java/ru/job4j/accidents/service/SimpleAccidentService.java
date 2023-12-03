package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;
import ru.job4j.accidents.repository.AccidentRepository;
import ru.job4j.accidents.repository.AccidentTypeJdbcTemplate;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {


    private final AccidentJdbcTemplate accidentRepository;

    public Accident create(Accident accident) {
        return accidentRepository.create(accident);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentRepository.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    @Override
    public boolean update(Accident accident) {
        return accidentRepository.update(accident);
    }
}
