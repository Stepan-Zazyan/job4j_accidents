package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accidents.model.Rule;

public interface RuleRepositorySpringData extends JpaRepository<Rule, Integer> {
}

