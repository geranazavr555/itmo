package ru.itmo.sdcourse.hw4.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto implements Serializable {
    private Long taskId;

    private long taskListId;

    @NotEmpty
    @Size(min = 1, max = 256)
    private String name;

    @Size(max = 65536)
    private String description;

    private boolean done;
}