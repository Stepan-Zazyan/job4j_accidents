package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplate implements AccidentTypeRepository {

    private final JdbcTemplate jdbc;

    @Override
    public AccidentType create(AccidentType accidentType) {
        jdbc.update("insert into accident_types (name) values (?)",
                accidentType.getName());
        return accidentType;
    }

    @Override
    public Collection<AccidentType> findAll() {
        return jdbc.query("select id, name from accident_types",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("select id, name from accident_types where id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                }, id));
    }
}
