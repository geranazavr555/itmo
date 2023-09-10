package ru.itmo.sdcourse.hw4.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskListDto implements Serializable {
    private Long taskListId;

    @NotEmpty
    @Size(min = 1, max = 256)
    private String name;
}