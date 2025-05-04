package ru.dugarov.servicetodo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dugarov.servicetodo.entity.Task;
import ru.dugarov.servicetodo.entity.TaskStatus;
import ru.dugarov.servicetodo.model.TaskDto;
import ru.dugarov.servicetodo.repository.TaskRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskDto createTask(TaskDto request) {
        Task task = Task.builder()
                .name(request.getName())
                .description(request.getDescription())
                .status(TaskStatus.NOT_COMPLETED)
                .createTime(Instant.now())
                .build();

        taskRepository.save(task);
        return buildResponse(task);
    }


    public List<TaskDto> getTasks(TaskStatus status) {
        List<Task> tasks = status != null
                ? taskRepository.findAllByStatus(status)
                : taskRepository.findAll();

        return tasks.stream()
                .map(this::buildResponse)
                .toList();
    }

    public TaskDto getTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Задача не найдена"));
        return buildResponse(task);
    }

    public TaskDto updateTask(Long id, TaskDto request) {
        return taskRepository.findById(id)
                .map(t -> {
                    t.setName(request.getName());
                    t.setDescription(request.getDescription());
                    t.setStatus(request.getStatus());
                    return buildResponse(taskRepository.save(t));
                })
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
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
                .build();
    }
}
