package ru.dugarov.servicetodo.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.dugarov.servicetodo.entity.TaskStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private TaskStatus status;
}
