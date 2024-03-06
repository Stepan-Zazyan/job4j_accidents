package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String name);
}