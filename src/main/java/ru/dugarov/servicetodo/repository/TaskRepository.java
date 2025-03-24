package ru.dugarov.servicetodo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.dugarov.servicetodo.entity.Task;
import ru.dugarov.servicetodo.entity.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatus(TaskStatus status);
}
