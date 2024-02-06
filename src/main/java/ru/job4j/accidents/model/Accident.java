package ru.job4j.accidents.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accidents")
@Getter
@Setter
public class Accident {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accident_type_id")
    private AccidentType type;

    @ManyToMany
    @JoinTable(
            name = "accidents_and_rules",
            joinColumns = { @JoinColumn(name = "accidents_id") },
            inverseJoinColumns = { @JoinColumn(name = "rules_id") }
    )
    private Set<Rule> rules = new HashSet<>();
}