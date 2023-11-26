package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@Service
public class SimpleRuleService implements RuleService {

    private final RuleRepository rulesRepository;
    @Override
    public Collection<Rule> findAll() {
        return rulesRepository.findAll();
    }

    @Override
    public Set<Rule> getRulesByIds(String[] arrayOfIds) {
        return rulesRepository.getRulesByIds(arrayOfIds);
    }
}
