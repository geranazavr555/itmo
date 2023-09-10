package ru.itmo.sdcourse.hw4.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.sdcourse.hw4.dto.TaskDto;
import ru.itmo.sdcourse.hw4.dto.TaskListDto;
import ru.itmo.sdcourse.hw4.service.TaskListService;
import ru.itmo.sdcourse.hw4.service.TaskService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class TaskController extends BaseController {
    private TaskListService taskListService;
    private TaskService taskService;

    @GetMapping("/list/{taskListId}/tasks/new")
    public String newTask(@PathVariable long taskListId, HttpSession httpSession, Model model) {
        Optional<TaskListDto> taskListDto = ensureTaskList(taskListId, httpSession);
        if (taskListDto.isEmpty())
            return "redirect:/";

        TaskDto taskDto = new TaskDto();
        taskDto.setTaskListId(taskListId);
        model.addAttribute("taskDto", taskDto);
        return "taskEdit";
    }

    @PostMapping("/list/{taskListId}/tasks/new")
    public String newTaskPost(@PathVariable long taskListId, HttpSession httpSession,
                              @Valid @ModelAttribute("taskDto") TaskDto taskDto, BindingResult bindingResult) {
        Optional<TaskListDto> taskListDto = ensureTaskList(taskListId, httpSession);
        if (taskListDto.isEmpty())
            return "redirect:/";

        if (bindingResult.hasErrors())
            return "taskEdit";

        taskService.save(taskDto, taskListDto.get());
        putMessage(httpSession, MessageLevel.SUCCESS, "Task \"%s\" created".formatted(taskDto.getName()));
        return "redirect:/#taskList" + taskListId;
    }


    @GetMapping("/list/{taskListId}/tasks/{taskId}")
    public String editTask(@PathVariable long taskListId, @PathVariable long taskId,
                           HttpSession httpSession, Model model) {
        Optional<TaskDto> taskDto = ensureTask(taskListId, taskId, httpSession);
        if (taskDto.isEmpty())
            return "redirect:/";

        model.addAttribute("taskDto", taskDto.get());
        return "taskEdit";
    }

    @PostMapping("/list/{taskListId}/tasks/{taskId}")
    public String editTaskPost(@PathVariable long taskListId, @PathVariable long taskId, HttpSession httpSession,
                               @Valid @ModelAttribute("taskDto") TaskDto taskDto, BindingResult bindingResult) {
        Optional<TaskDto> taskDto1 = ensureTask(taskListId, taskId, httpSession);
        if (taskDto1.isEmpty())
            return "redirect:/";

        if (bindingResult.hasErrors())
            return "taskEdit";

        Optional<TaskListDto> taskListDto = ensureTaskList(taskListId, httpSession);
        if (taskListDto.isEmpty())
            return "redirect:/";

        taskService.save(taskDto, taskListDto.get());
        putMessage(httpSession, MessageLevel.SUCCESS, "Task \"%s\" saved".formatted(taskDto.getName()));
        return "redirect:/#taskList" + taskListId;
    }

    @PostMapping("/list/{taskListId}/tasks/{taskId}/delete")
    public String deleteTaskPost(@PathVariable long taskListId, @PathVariable long taskId, HttpSession httpSession) {
        Optional<TaskDto> taskDto = ensureTask(taskListId, taskId, httpSession);
        if (taskDto.isPresent()) {
            taskService.delete(taskDto.get());
            putMessage(httpSession, MessageLevel.WARN, "Task \"%s\" deleted".formatted(taskDto.get().getName()));
        }
        return "redirect:/#taskList" + taskListId;
    }

    @ResponseBody
    @PostMapping("/list/{taskListId}/tasks/{taskId}/done")
    public String donePost(@PathVariable long taskListId, @PathVariable long taskId, @RequestParam boolean done,
                           HttpSession httpSession) {
        Optional<TaskDto> taskDto = ensureTask(taskListId, taskId, httpSession);
        if (taskDto.isEmpty())
            return "\"fail\"";

        taskService.changeDone(taskId, done);
        ;
        return "\"ok\"";
    }

    private Optional<TaskListDto> ensureTaskList(long taskListId, HttpSession httpSession) {
        Optional<TaskListDto> taskListDto = taskListService.find(taskListId, getCurrentUser(httpSession));
        if (taskListDto.isEmpty())
            putMessage(httpSession, MessageLevel.ERROR, "No such task list");
        return taskListDto;
    }

    private Optional<TaskDto> ensureTask(long taskListId, long taskId, HttpSession httpSession) {
        Optional<TaskListDto> taskListDto = ensureTaskList(taskListId, httpSession);
        if (taskListDto.isEmpty())
            return Optional.empty();

        Optional<TaskDto> taskDto = taskService.find(taskListDto.get(), taskId);
        if (taskDto.isEmpty())
            putMessage(httpSession, MessageLevel.ERROR, "No such task");

        return taskDto;
    }
}
