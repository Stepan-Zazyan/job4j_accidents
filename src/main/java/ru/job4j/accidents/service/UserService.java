package ru.job4j.accidents.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepositorySpringData;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepositorySpringData userRepository;

    public void existsByUsername(String name) {
        if (userRepository.existsByUsername(name)) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }
    }

    public Optional<User> save(User user) {
        userRepository.save(user);
        return Optional.of(user);
    }

}
