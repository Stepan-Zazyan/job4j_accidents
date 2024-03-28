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

    public Optional<User> save(User user) {
        return userRepository.existsByUsername(user.getUsername())
                ? Optional.empty() : Optional.of(user);
    }

}
