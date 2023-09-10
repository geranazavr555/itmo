package ru.itmo.sdcourse.hw4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long taskListId;

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 1, max = 256)
    private String name;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean deleted;

    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "DATETIME DEFAULT NOW()")
    private Date creationTime;

    @ToString.Exclude
    @OneToMany(mappedBy = "taskList")
    @OrderBy("done ASC, creationTime DESC")
    private List<Task> tasks = new ArrayList<>();

}
