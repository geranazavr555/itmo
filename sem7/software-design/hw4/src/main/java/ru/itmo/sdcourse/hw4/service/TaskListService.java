package ru.itmo.sdcourse.hw4.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.sdcourse.hw4.dto.TaskListDto;
import ru.itmo.sdcourse.hw4.model.TaskList;
import ru.itmo.sdcourse.hw4.model.User;
import ru.itmo.sdcourse.hw4.repository.TaskListRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskListService {
    private TaskListRepository taskListRepository;

    public void save(TaskListDto taskListDto, User user) {
        TaskList taskList = new TaskList();
        taskList.setName(taskListDto.getName());
        taskList.setUser(user);

        if (taskListDto.getTaskListId() != null)
            taskList.setTaskListId(taskListDto.getTaskListId());

        taskListRepository.save(taskList);
    }

    public Optional<TaskListDto> find(long taskListId, User user) {
        return taskListRepository.findByTaskListIdAndUserAndDeletedFalse(taskListId, user)
                .map(taskList -> new TaskListDto(taskList.getTaskListId(), taskList.getName()));
    }

    public void delete(TaskListDto taskListDto) {
        Optional<TaskList> taskList = taskListRepository.findById(taskListDto.getTaskListId());
        taskList.ifPresent(taskList1 -> {
            taskList1.setDeleted(true);
            taskListRepository.save(taskList1);
        });
    }
}
