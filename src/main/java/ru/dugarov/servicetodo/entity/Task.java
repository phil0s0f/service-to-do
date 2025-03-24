package ru.dugarov.servicetodo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.NOT_COMPLETED;

    private Instant createTime = Instant.now();
}
