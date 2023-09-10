package ru.itmo.sdcourse.hw4.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.sdcourse.hw4.dto.TaskDto;
import ru.itmo.sdcourse.hw4.dto.TaskListDto;
import ru.itmo.sdcourse.hw4.model.Task;
import ru.itmo.sdcourse.hw4.model.TaskList;
import ru.itmo.sdcourse.hw4.repository.TaskListRepository;
import ru.itmo.sdcourse.hw4.repository.TaskRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TaskService {
    private TaskListRepository taskListRepository;
    private TaskRepository taskRepository;

    public Optional<TaskDto> find(TaskListDto taskListDto, long taskId) {
        Long taskListId = taskListDto.getTaskListId();
        if (taskListId == null)
            return Optional.empty();

        Optional<TaskList> taskList = taskListRepository.findById(taskListId);
        return taskList.flatMap(list -> taskRepository.findByTaskListAndTaskIdAndDeletedFalse(list, taskId)
                .map(this::mapToDto));

    }

    public void changeDone(long taskId, boolean done) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setDone(done);
            taskRepository.save(task);
        }
    }

    public void save(TaskDto taskDto, TaskListDto taskListDto) {
        Long taskListId = taskListDto.getTaskListId();
        if (taskListId == null)
            return;

        Optional<TaskList> taskList = taskListRepository.findById(taskListId);
        if (taskList.isEmpty())
            return;

        Task task = new Task();
        task.setTaskList(taskList.get());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription() == null ? "" : taskDto.getDescription());

        if (taskDto.getTaskId() != null)
            task.setTaskId(taskDto.getTaskId());

        taskRepository.save(task);
    }

    public void delete(TaskDto taskDto) {
        Optional<Task> taskOptional = taskRepository.findById(taskDto.getTaskId());
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setDeleted(true);
            taskRepository.save(task);
        }
    }

    private TaskDto mapToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(task.getTaskId());
        taskDto.setTaskListId(task.getTaskList().getTaskListId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setDone(task.isDone());
        return taskDto;
    }
}
