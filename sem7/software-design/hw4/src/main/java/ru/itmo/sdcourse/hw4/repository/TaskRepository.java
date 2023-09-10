package ru.itmo.sdcourse.hw4.repository;

import ru.itmo.sdcourse.hw4.model.Task;
import ru.itmo.sdcourse.hw4.model.TaskList;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends ApplicationRepository<Task> {
    List<Task> findAllByTaskListAndDeletedFalseOrderByDoneAscCreationTimeDesc(TaskList taskList);
    Optional<Task> findByTaskListAndTaskIdAndDeletedFalse(TaskList taskList, long taskId);
}
