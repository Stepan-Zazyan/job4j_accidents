package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccidentTypeMem implements AccidentTypeRepository {

    private final AtomicInteger id = new AtomicInteger(3);

    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>() {
        {
            put(1, new AccidentType(2, "Машина и человек"));
            put(2, new AccidentType(1, "Две машины"));
            put(3, new AccidentType(3, "Машина и велосипед"));
        }
    };

    @Override
    public AccidentType create(AccidentType accidentType) {
        accidentType.setId(id.incrementAndGet());
        accidentTypes.put(accidentType.getId(), accidentType);
        return accidentType;
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypes.values();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(accidentTypes.get(id));
    }
}
