package ru.dugarov.servicetodo.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dugarov.servicetodo.dto.TaskDto;
import ru.dugarov.servicetodo.entity.Task;
import ru.dugarov.servicetodo.entity.TaskStatus;
import ru.dugarov.servicetodo.entity.User;
import ru.dugarov.servicetodo.repository.TaskRepository;
import ru.dugarov.servicetodo.repository.UserRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskDto createTask(TaskDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        Task task = Task.builder()
                .name(request.getName())
                .description(request.getDescription())
                .status(TaskStatus.NOT_COMPLETED)
                .createTime(Instant.now())
                .user(user)
                .build();

        return buildResponse(taskRepository.save(task));
    }


    public List<TaskDto> getTasksByStatus(TaskStatus status) {
        List<Task> tasks = status != null
                ? taskRepository.findAllByStatus(status)
                : taskRepository.findAll();

        return tasks.stream()
                .map(this::buildResponse)
                .toList();
    }

    public List<TaskDto> getTasksByUser(Long userId) {
        return taskRepository.findByUser_Id(userId).stream()
                .map(this::buildResponse)
                .toList();
    }

    public TaskDto getTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Задача не найдена"));
        return buildResponse(task);
    }

    public TaskDto updateTask(Long id, TaskDto request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Задача не найдена"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setUser(user);

        return buildResponse(taskRepository.save(task));
    }

    public String deleteTask(Long id) {
        taskRepository.deleteById(id);
        return "Задача " + id + " удаленна";
    }

    private TaskDto buildResponse(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .status(task.getStatus())
                .createTime(task.getCreateTime())
                .userId(task.getUser().getId())
                .build();
    }
}
