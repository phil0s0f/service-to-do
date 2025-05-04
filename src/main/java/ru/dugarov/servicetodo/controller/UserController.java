package ru.dugarov.servicetodo.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dugarov.servicetodo.dto.TaskDto;
import ru.dugarov.servicetodo.dto.UserDto;
import ru.dugarov.servicetodo.service.TaskService;
import ru.dugarov.servicetodo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByUserId(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(taskService.getTasksByUser(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto UserDto) {
        return ResponseEntity.ok(userService.createUser(UserDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable @NotNull Long id, @RequestBody @Valid UserDto UserDto) {
        return ResponseEntity.ok(userService.updateUser(id, UserDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
