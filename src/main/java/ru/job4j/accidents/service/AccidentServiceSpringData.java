package ru.job4j.accidents.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepositorySpringData;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentServiceSpringData {

    private final AccidentRepositorySpringData accidentRepository;

    public void create(Accident accident) {
        accidentRepository.save(accident);
    }

    public List<Accident> getAll() {
        return (List<Accident>) accidentRepository.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public boolean update(Accident accident) {
        Accident accidentToBeUpdated = accidentRepository.findById(accident.getId()).get();
        accidentToBeUpdated.setName(accident.getName());
        accidentToBeUpdated.setText(accident.getText());
        accidentToBeUpdated.setType(accident.getType());
        accidentToBeUpdated.setAddress(accident.getAddress());
        accidentToBeUpdated.setRules(accident.getRules());
        return !accident.equals(accidentToBeUpdated);
    }

}
