package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RuleMem implements RuleRepository {

    List<Rule> rules = List.of(
            new Rule(1, "Статья. 1"),
            new Rule(2, "Статья. 2"),
            new Rule(3, "Статья. 3")
    );

    @Override
    public Collection<Rule> findAll() {
        return rules;
    }

    @Override
    public Set<Rule> getRulesByIds(String[] arrayOfIds) {
        Set<Rule> ruleSet = new HashSet<>();
        for (int i = 0; i < arrayOfIds.length; i++) {
            ruleSet.add(rules.get(i));
        }
        return ruleSet;
    }


}
