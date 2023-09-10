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
@Table(name = "Users", indexes = {
        @Index(name = "idx_user_userid", columnList = "userId"),
        @Index(name = "idx_user_login", columnList = "login, passwordHash")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long userId;

    @Column(nullable = false, unique = true)
    @NotEmpty
    @Size(min = 5, max = 32)
    private String login;

    @Column(nullable = false)
    @NotEmpty
    @Size(max = 256)
    private String passwordHash;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT NOW()")
    private Date creationTime;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @OrderBy("creationTime DESC")
    private List<TaskList> taskLists = new ArrayList<>();

}



