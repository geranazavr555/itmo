package ru.itmo.sdcourse.hw4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long taskId;

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "taskListId", nullable = false)
    private TaskList taskList;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 1, max = 256)
    private String name;

    @Column(nullable = false)
    @NotNull
    @Lob
    @Size(max = 65536)
    private String description;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean deleted;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean done;

    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "DATETIME DEFAULT NOW()")
    private Date creationTime;

}
