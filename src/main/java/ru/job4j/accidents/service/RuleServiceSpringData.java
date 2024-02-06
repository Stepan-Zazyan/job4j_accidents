package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepositorySpringData;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleServiceSpringData {
    private final RuleRepositorySpringData ruleRepository;

    public void create(Rule rule) {
        ruleRepository.save(rule);
    }

    public List<Rule> getAll() {
        return (List<Rule>) ruleRepository.findAll();
    }

    public Set<Rule> getRulesByIds(String[] arrayOfRules) {
        Set<Rule> ruleSet = new HashSet<>();
        List<Rule> ruleCollection = getAll();
        for (int i = 1; i <= arrayOfRules.length; i++) {
            ruleSet.add(ruleCollection.get(i));
        }
        return ruleSet;
    }

    public Optional<Rule> findById(int id) {
        return ruleRepository.findById(id);
    }

    public boolean update(Rule rule) {
        Rule ruleToBeUpdated = ruleRepository.findById(rule.getId()).get();
        ruleToBeUpdated.setName(rule.getName());
        return !rule.equals(ruleToBeUpdated);
    }


}
