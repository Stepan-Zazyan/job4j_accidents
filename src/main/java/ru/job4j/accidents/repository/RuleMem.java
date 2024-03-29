package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RuleMem implements RuleRepository {

    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>() {
        {
            put(1, new Rule(1, "Статья. 1"));
            put(2, new Rule(2, "Статья. 2"));
            put(3, new Rule(3, "Статья. 3"));
        }
    };

    @Override
    public Collection<Rule> findAll() {
        return rules.values();
    }

    @Override
    public Set<Rule> getRulesByIds(String[] arrayOfIds) {
        return Stream.of(rules)
                .flatMap(s -> s.values().stream())
                .filter(t -> Arrays.stream(arrayOfIds).anyMatch(e -> t.getName().contains(e)))
                .collect(Collectors.toSet());
    }


}
