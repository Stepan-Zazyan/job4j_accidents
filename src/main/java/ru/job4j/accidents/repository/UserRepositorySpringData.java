package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.User;

import java.util.Optional;

@Repository
public interface UserRepositorySpringData extends JpaRepository<User, Integer> {
    boolean existsByUsername(String name);

}