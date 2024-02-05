package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentHibernate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private final AccidentHibernate accidentsRepository;

    public Accident create(Accident accident) {
        return accidentsRepository.create(accident);
    }

    public List<Accident> getAll() {
        return accidentsRepository.getAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentsRepository.findById(id);
    }

    @Override
    public boolean update(Accident accident) {
        return accidentsRepository.update(accident);
    }
}
