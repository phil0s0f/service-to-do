package ru.dugarov.servicetodo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.dugarov.servicetodo.entity.TaskStatus;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    @NotBlank
    private String name;
    private String description;
    private TaskStatus status;
    private Instant createTime;
    private Long userId;
}
