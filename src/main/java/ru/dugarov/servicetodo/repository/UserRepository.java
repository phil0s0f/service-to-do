package ru.dugarov.servicetodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dugarov.servicetodo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
