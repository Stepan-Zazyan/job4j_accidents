package ru.job4j.accidents.repository;


import lombok.AllArgsConstructor;
import org.apache.tomcat.util.digester.Rules;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplate implements RuleRepository {

    private final JdbcTemplate jdbc;

    @Override
    public Collection<Rule> findAll() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    @Override
    public Set<Rule> getRulesByIds(String[] arrayOfIds) {
        Set<Rule> ruleSet = new HashSet<>();
        List<Rule> ruleCollection = (List<Rule>) findAll();
        for (int i = 1; i <= arrayOfIds.length; i++) {
            ruleSet.add(ruleCollection.get(i));
        }
        return ruleSet;
    }
}
