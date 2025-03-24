package ru.dugarov.servicetodo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dugarov.servicetodo.entity.Task;
import ru.dugarov.servicetodo.entity.TaskStatus;
import ru.dugarov.servicetodo.model.TaskRequest;
import ru.dugarov.servicetodo.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task createTask(TaskRequest request) {
        Task task = Task.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        taskRepository.save(task);

        return task;
    }

    public List<Task> getTasks(TaskStatus status) {
        return status != null ? taskRepository.findAllByStatus(status) : taskRepository.findAll();
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Задача не найдена"));
    }

    public Task updateTask(Long id, TaskRequest request) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setName(request.getName());
                    task.setDescription(request.getDescription());
                    task.setStatus(request.getStatus());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
    }

    public String deleteTask(Long id) {
        taskRepository.deleteById(id);
        return "Задача " + id + " удаленна";
    }
}
