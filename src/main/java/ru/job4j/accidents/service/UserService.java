package ru.job4j.accidents.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepositorySpringData;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepositorySpringData userRepository;

    public void existsByUsername(String name) {
        if (userRepository.existsByUsername(name)) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
