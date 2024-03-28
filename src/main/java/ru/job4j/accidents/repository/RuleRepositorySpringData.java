package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accidents.model.Rule;

import java.util.Set;

public interface RuleRepositorySpringData extends JpaRepository<Rule, Integer> {
    Set<Rule> getRulesById(String[] arrayOfIds);
}

