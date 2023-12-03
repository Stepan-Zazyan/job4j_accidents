package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentRepository {

    private final JdbcTemplate jdbc;

    public Accident create(Accident accident) {
        jdbc.update("insert into accidents (name, text, address) values (?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress());
        return accident;
    }

    public List<Accident> findAll() {
        return jdbc.query("select id, name, text, address from accidents",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                });
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("select id, name, text, address from accidents where id = ?",
                (resultSet, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setId(resultSet.getInt("id"));
                    accident.setName(resultSet.getString("name"));
                    accident.setText(resultSet.getString("text"));
                    accident.setAddress(resultSet.getString("address"));
                    return accident;
                }, id));
    }

    @Override
    public boolean update(Accident accident) {
        return jdbc.update("update accidents set name = ?, text = ?, address = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getId()) != 0;
    }
}
