package ru.itmo.sdcourse.hw4.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itmo.sdcourse.hw4.dto.TaskListDto;
import ru.itmo.sdcourse.hw4.dto.UserRegistrationDto;
import ru.itmo.sdcourse.hw4.model.TaskList;
import ru.itmo.sdcourse.hw4.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class TaskListServiceTest {
    private static final Random random = new Random();

    @Autowired
    private UserService userService;

    @Autowired
    private TaskListService taskListService;

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

    @Test
    public void testSimple() {
        User user = prepareUser();

        TaskListDto taskListDto = new TaskListDto();
        taskListDto.setName("name");
        taskListService.save(taskListDto, user);

        user = updateUser(user);

        List<TaskList> taskLists = user.getTaskLists();
        Assertions.assertEquals(1, taskLists.size());
        Assertions.assertEquals("name", taskLists.get(0).getName());
    }

    @Test
    public void testUpdate() {
        User user = prepareUser();

        TaskListDto taskListDto = new TaskListDto();
        taskListDto.setName("name");
        taskListService.save(taskListDto, user);

        user = updateUser(user);

        List<TaskList> taskLists = user.getTaskLists();
        Assertions.assertEquals(1, taskLists.size());
        Assertions.assertEquals("name", taskLists.get(0).getName());

        taskListDto.setName("kek");
        taskListDto.setTaskListId(taskLists.get(0).getTaskListId());
        taskListService.save(taskListDto, user);

        user = updateUser(user);
        taskLists = user.getTaskLists();
        Assertions.assertEquals(1, taskLists.size());
        Assertions.assertEquals("kek", taskLists.get(0).getName());
    }

    @Test
    public void testFind() {
        User user = prepareUser();

        TaskListDto taskListDto = new TaskListDto();
        taskListDto.setName("name");
        taskListService.save(taskListDto, user);

        user = updateUser(user);

        List<TaskList> taskLists = user.getTaskLists();
        Assertions.assertEquals(1, taskLists.size());
        Assertions.assertEquals("name", taskLists.get(0).getName());

        Optional<TaskListDto> taskListDto1 = taskListService.find(taskLists.get(0).getTaskListId(), user);
        Assertions.assertTrue(taskListDto1.isPresent());

        Assertions.assertEquals(taskLists.get(0).getTaskListId(), taskListDto1.get().getTaskListId());
        Assertions.assertEquals(taskLists.get(0).getName(), taskListDto1.get().getName());

        Optional<TaskListDto> taskListDto2 = taskListService.find(taskLists.get(0).getTaskListId(), prepareUser());
        Assertions.assertTrue(taskListDto2.isEmpty());
    }

    @Test
    public void testDelete() {
        User user = prepareUser();

        TaskListDto taskListDto = new TaskListDto();
        taskListDto.setName("name");
        taskListService.save(taskListDto, user);

        user = updateUser(user);

        List<TaskList> taskLists = user.getTaskLists();
        Assertions.assertEquals(1, taskLists.size());
        Assertions.assertEquals("name", taskLists.get(0).getName());

        Optional<TaskListDto> taskListDto1 = taskListService.find(taskLists.get(0).getTaskListId(), user);
        Assertions.assertTrue(taskListDto1.isPresent());

        Assertions.assertEquals(taskLists.get(0).getTaskListId(), taskListDto1.get().getTaskListId());
        Assertions.assertEquals(taskLists.get(0).getName(), taskListDto1.get().getName());

        taskListService.delete(taskListDto1.get());

        taskListDto1 = taskListService.find(taskLists.get(0).getTaskListId(), user);
        Assertions.assertTrue(taskListDto1.isEmpty());
    }
}
