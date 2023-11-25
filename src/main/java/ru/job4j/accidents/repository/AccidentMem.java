package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem implements AccidentRepository {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>() {
        {
            put(1, new Accident(1, "crash1", "text1", "address1"));
            put(2, new Accident(2, "crash2", "text2", "address2"));
            put(3, new Accident(3, "crash3", "text3", "address3"));
        }
    };

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public Accident findById(int id) {
        return accidents.get(id);
    }
}
