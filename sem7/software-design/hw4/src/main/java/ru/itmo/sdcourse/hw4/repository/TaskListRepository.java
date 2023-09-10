package ru.itmo.sdcourse.hw4.repository;

import ru.itmo.sdcourse.hw4.model.TaskList;
import ru.itmo.sdcourse.hw4.model.User;

import java.util.Optional;

public interface TaskListRepository extends ApplicationRepository<TaskList> {
    Optional<TaskList> findByTaskListIdAndUserAndDeletedFalse(long taskListId, User user);
}
