package ru.job4j.accidents.model;


import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @EqualsAndHashCode.Include
    private int id;

    private String name;

    private String login;

    private String password;

}
