package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentRepository {

    private final AtomicInteger id = new AtomicInteger(3);

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>() {
        {
            put(1, new Accident(1, "crash1", "text1", "address1", new AccidentType(2, "Машина и человек")));
            put(2, new Accident(2, "crash2", "text2", "address2", new AccidentType(1, "Две машины")));
            put(3, new Accident(3, "crash3", "text3", "address3", new AccidentType(3, "Машина и велосипед")));
        }
    };

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public Accident create(Accident accident) {
        accident.setId(id.incrementAndGet());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        return (accidents.computeIfPresent(accident.getId(), (idOld, oldAccident) ->
        new Accident(oldAccident.getId(), accident.getName(),
                accident.getText(), accident.getAddress(), accident.getType()))) != null;
    }
}
