package ru.dugarov.servicetodo.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dugarov.servicetodo.entity.Task;
import ru.dugarov.servicetodo.entity.TaskStatus;
import ru.dugarov.servicetodo.model.TaskRequest;
import ru.dugarov.servicetodo.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task task = taskService.createTask(taskRequest);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@RequestParam(required = false) TaskStatus status) {
        List<Task> tasks = taskService.getTasks(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable @NotNull Long id) {
        Task task = taskService.getTask(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable @NotNull Long id, @RequestBody @Valid TaskRequest taskRequest) {
        Task task = taskService.updateTask(id, taskRequest);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable @NotNull Long id) {
        String result = taskService.deleteTask(id);
        return ResponseEntity.ok(result);
    }
}
