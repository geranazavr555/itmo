package ru.itmo.sdcourse.hw4.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itmo.sdcourse.hw4.dto.TaskDto;
import ru.itmo.sdcourse.hw4.dto.TaskListDto;
import ru.itmo.sdcourse.hw4.dto.UserRegistrationDto;
import ru.itmo.sdcourse.hw4.model.Task;
import ru.itmo.sdcourse.hw4.model.TaskList;
import ru.itmo.sdcourse.hw4.model.User;
import ru.itmo.sdcourse.hw4.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class TaskServiceTest {
    private static final Random random = new Random();

    @Autowired
    private UserService userService;

    @Autowired
    private TaskListService taskListService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    private User prepareUser() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setLogin("login" + random.nextInt());
        userRegistrationDto.setPassword("password"+ random.nextInt());
        userRegistrationDto.setPasswordConfirmation(userRegistrationDto.getPassword());
        userService.register(userRegistrationDto);

        Optional<User> userOptional = userService.findByLogin(userRegistrationDto.getLogin());
        Assertions.assertTrue(userOptional.isPresent());

        return userOptional.get();
    }

    private User updateUser(User user) {
        Optional<User> userOptional = userService.findByLogin(user.getLogin());
        Assertions.assertTrue(userOptional.isPresent());

        return userOptional.get();
    }

    private TaskList prepareTaskList(User user) {
        TaskListDto taskListDto = new TaskListDto();
        taskListDto.setName("name" + random.nextInt());
        taskListService.save(taskListDto, user);

        user = updateUser(user);

        List<TaskList> taskLists = user.getTaskLists();
        Assertions.assertEquals(1, taskLists.size());
        Assertions.assertEquals(taskListDto.getName(), taskLists.get(0).getName());
        return taskLists.get(0);
    }

    private TaskListDto prepareTaskListDto(TaskList taskList) {
        TaskListDto taskListDto = new TaskListDto();
        taskListDto.setTaskListId(taskList.getTaskListId());
        taskListDto.setName(taskList.getName());
        return taskListDto;
    }

    @Test
    public void testSave() throws InterruptedException {
        User user = prepareUser();
        TaskList taskList = prepareTaskList(user);
        TaskListDto taskListDto = prepareTaskListDto(taskList);

        TaskDto taskDto = new TaskDto();
        taskDto.setName("name1");
        taskDto.setDescription("desc1");
        taskService.save(taskDto, taskListDto);

        List<Task> tasks = taskRepository.findAllByTaskListAndDeletedFalseOrderByDoneAscCreationTimeDesc(taskList);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertEquals(taskDto.getName(), tasks.get(0).getName());
        Assertions.assertEquals(taskDto.getDescription(), tasks.get(0).getDescription());

        Thread.sleep(1000);

        TaskDto taskDto2 = new TaskDto();
        taskDto2.setName("name2");
        taskDto2.setDescription("desc2");
        taskService.save(taskDto2, taskListDto);

        tasks = taskRepository.findAllByTaskListAndDeletedFalseOrderByDoneAscCreationTimeDesc(taskList);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        Assertions.assertEquals(2, tasks.size());
        Assertions.assertEquals(taskDto2.getName(), tasks.get(0).getName());
        Assertions.assertEquals(taskDto2.getDescription(), tasks.get(0).getDescription());
        Assertions.assertEquals(taskDto.getName(), tasks.get(1).getName());
        Assertions.assertEquals(taskDto.getDescription(), tasks.get(1).getDescription());
    }

    @Test
    public void testDelete() throws InterruptedException {
        User user = prepareUser();
        TaskList taskList = prepareTaskList(user);
        TaskListDto taskListDto = prepareTaskListDto(taskList);

        TaskDto taskDto = new TaskDto();
        taskDto.setName("name1");
        taskDto.setDescription("desc1");
        taskService.save(taskDto, taskListDto);

        List<Task> tasks = taskRepository.findAllByTaskListAndDeletedFalseOrderByDoneAscCreationTimeDesc(taskList);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertEquals(taskDto.getName(), tasks.get(0).getName());
        Assertions.assertEquals(taskDto.getDescription(), tasks.get(0).getDescription());

        Thread.sleep(1000);

        TaskDto taskDto2 = new TaskDto();
        taskDto2.setName("name2");
        taskDto2.setDescription("desc2");
        taskService.save(taskDto2, taskListDto);

        tasks = taskRepository.findAllByTaskListAndDeletedFalseOrderByDoneAscCreationTimeDesc(taskList);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        Assertions.assertEquals(2, tasks.size());
        Assertions.assertEquals(taskDto2.getName(), tasks.get(0).getName());
        Assertions.assertEquals(taskDto2.getDescription(), tasks.get(0).getDescription());
        Assertions.assertEquals(taskDto.getName(), tasks.get(1).getName());
        Assertions.assertEquals(taskDto.getDescription(), tasks.get(1).getDescription());

        taskDto2.setTaskId(tasks.get(0).getTaskId());
        taskService.delete(taskDto2);

        tasks = taskRepository.findAllByTaskListAndDeletedFalseOrderByDoneAscCreationTimeDesc(taskList);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertEquals(taskDto.getName(), tasks.get(0).getName());
        Assertions.assertEquals(taskDto.getDescription(), tasks.get(0).getDescription());
    }

    @Test
    public void testFind() throws InterruptedException {
        User user = prepareUser();
        TaskList taskList = prepareTaskList(user);
        TaskListDto taskListDto = prepareTaskListDto(taskList);

        TaskDto taskDto = new TaskDto();
        taskDto.setName("name1");
        taskDto.setDescription("desc1");
        taskService.save(taskDto, taskListDto);

        List<Task> tasks = taskRepository.findAllByTaskListAndDeletedFalseOrderByDoneAscCreationTimeDesc(taskList);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertEquals(taskDto.getName(), tasks.get(0).getName());
        Assertions.assertEquals(taskDto.getDescription(), tasks.get(0).getDescription());

        Thread.sleep(1000);

        TaskDto taskDto2 = new TaskDto();
        taskDto2.setName("name2");
        taskDto2.setDescription("desc2");
        taskService.save(taskDto2, taskListDto);

        tasks = taskRepository.findAllByTaskListAndDeletedFalseOrderByDoneAscCreationTimeDesc(taskList);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        Assertions.assertEquals(2, tasks.size());
        Assertions.assertEquals(taskDto2.getName(), tasks.get(0).getName());
        Assertions.assertEquals(taskDto2.getDescription(), tasks.get(0).getDescription());
        Assertions.assertEquals(taskDto.getName(), tasks.get(1).getName());
        Assertions.assertEquals(taskDto.getDescription(), tasks.get(1).getDescription());

        Optional<TaskDto> taskDto1 = taskService.find(taskListDto, tasks.get(1).getTaskId());
        Assertions.assertTrue(taskDto1.isPresent());
        Assertions.assertEquals(tasks.get(1).getTaskId(), taskDto1.get().getTaskId());
        Assertions.assertEquals(tasks.get(1).getName(), taskDto1.get().getName());
        Assertions.assertEquals(tasks.get(1).getDescription(), taskDto1.get().getDescription());
        Assertions.assertEquals(taskListDto.getTaskListId(), taskDto1.get().getTaskListId());

        taskDto1 = taskService.find(taskListDto, 1337555);
        Assertions.assertTrue(taskDto1.isEmpty());
    }

    @Test
    public void testChangeDone() throws InterruptedException {
        User user = prepareUser();
        TaskList taskList = prepareTaskList(user);
        TaskListDto taskListDto = prepareTaskListDto(taskList);

        TaskDto taskDto = new TaskDto();
        taskDto.setName("name1");
        taskDto.setDescription("desc1");
        taskService.save(taskDto, taskListDto);

        List<Task> tasks = taskRepository.findAllByTaskListAndDeletedFalseOrderByDoneAscCreationTimeDesc(taskList);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertEquals(taskDto.getName(), tasks.get(0).getName());
        Assertions.assertEquals(taskDto.getDescription(), tasks.get(0).getDescription());

        Thread.sleep(1000);

        TaskDto taskDto2 = new TaskDto();
        taskDto2.setName("name2");
        taskDto2.setDescription("desc2");
        taskService.save(taskDto2, taskListDto);

        tasks = taskRepository.findAllByTaskListAndDeletedFalseOrderByDoneAscCreationTimeDesc(taskList);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        Assertions.assertEquals(2, tasks.size());
        Assertions.assertEquals(taskDto2.getName(), tasks.get(0).getName());
        Assertions.assertEquals(taskDto2.getDescription(), tasks.get(0).getDescription());
        Assertions.assertFalse(tasks.get(0).isDone());
        Assertions.assertEquals(taskDto.getName(), tasks.get(1).getName());
        Assertions.assertEquals(taskDto.getDescription(), tasks.get(1).getDescription());
        Assertions.assertFalse(tasks.get(1).isDone());

        taskService.changeDone(tasks.get(1).getTaskId(), true);

        tasks = taskRepository.findAllByTaskListAndDeletedFalseOrderByDoneAscCreationTimeDesc(taskList);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        Assertions.assertEquals(2, tasks.size());
        Assertions.assertEquals(taskDto2.getName(), tasks.get(0).getName());
        Assertions.assertEquals(taskDto2.getDescription(), tasks.get(0).getDescription());
        Assertions.assertFalse(tasks.get(0).isDone());
        Assertions.assertEquals(taskDto.getName(), tasks.get(1).getName());
        Assertions.assertEquals(taskDto.getDescription(), tasks.get(1).getDescription());
        Assertions.assertTrue(tasks.get(1).isDone());
    }
}
