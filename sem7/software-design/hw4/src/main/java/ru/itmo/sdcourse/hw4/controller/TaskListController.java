package ru.itmo.sdcourse.hw4.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.sdcourse.hw4.dto.TaskListDto;
import ru.itmo.sdcourse.hw4.service.TaskListService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class TaskListController extends BaseController {
    private TaskListService taskListService;

    @GetMapping({"", "/"})
    public String index() {
        return "dashboard";
    }

    @GetMapping("/list/new")
    public String newList(Model model) {
        model.addAttribute("taskListDto", new TaskListDto());
        return "taskListEdit";
    }

    @PostMapping("/list/new")
    public String newListPost(@Valid @ModelAttribute("taskListDto") TaskListDto taskListDto,
                              BindingResult bindingResult,
                              HttpSession httpSession) {
        if (bindingResult.hasErrors())
            return "taskListEdit";

        taskListService.save(taskListDto, getCurrentUser(httpSession));
        putMessage(httpSession, MessageLevel.SUCCESS, "Task list \"%s\" created".formatted(taskListDto.getName()));
        return "redirect:/";
    }

    @GetMapping("/list/{taskListId}/edit")
    public String editList(Model model, @PathVariable long taskListId, HttpSession httpSession) {
        Optional<TaskListDto> taskListDto = ensureTaskList(taskListId, httpSession);
        if (taskListDto.isEmpty())
            return "redirect:/";

        model.addAttribute("taskListDto", taskListDto.get());
        return "taskListEdit";
    }

    @PostMapping("/list/{taskListId}/edit")
    public String editListPost(@PathVariable long taskListId,
                               @Valid @ModelAttribute("taskListDto") TaskListDto taskListDto,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors())
            return "taskListEdit";

        Optional<TaskListDto> taskListDto1 = ensureTaskList(taskListId, httpSession);
        if (taskListDto1.isEmpty())
            return "redirect:/";

        taskListService.save(taskListDto, getCurrentUser(httpSession));
        putMessage(httpSession, MessageLevel.SUCCESS, "Task list \"%s\" updated".formatted(taskListDto.getName()));
        return "redirect:/";
    }

    @PostMapping("/list/{taskListId}/delete")
    public String deleteListPost(@PathVariable long taskListId, HttpSession httpSession) {
        Optional<TaskListDto> taskListDto = ensureTaskList(taskListId, httpSession);
        if (taskListDto.isPresent()) {
            taskListService.delete(taskListDto.get());
            putMessage(httpSession, MessageLevel.WARN, "Task list \"%s\" deleted".formatted(taskListDto.get().getName()));
        }
        return "redirect:/";
    }

    private Optional<TaskListDto> ensureTaskList(long taskListId, HttpSession httpSession) {
        Optional<TaskListDto> taskListDto = taskListService.find(taskListId, getCurrentUser(httpSession));
        if (taskListDto.isEmpty())
            putMessage(httpSession, MessageLevel.ERROR, "No such task list");
        return taskListDto;
    }
}
