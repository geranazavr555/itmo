package ru.itmo.sdcourse.hw4.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itmo.sdcourse.hw4.dto.UserEnterDto;
import ru.itmo.sdcourse.hw4.dto.UserRegistrationDto;
import ru.itmo.sdcourse.hw4.model.User;

import java.util.Optional;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class UserServiceTest {
    private static final Random random = new Random();

    @Autowired
    private UserService userService;

    @Test
    public void registerTest() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setLogin("login");
        userRegistrationDto.setPassword("password");
        userRegistrationDto.setPasswordConfirmation(userRegistrationDto.getPasswordConfirmation());
        userService.register(userRegistrationDto);

        Optional<User> user = userService.findByLogin("login");
        Assertions.assertTrue(user.isPresent());
        Assertions.assertNotEquals("password", user.get().getPasswordHash());
    }

    @Test
    public void enterTest() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setLogin("login1");
        userRegistrationDto.setPassword("password");
        userRegistrationDto.setPasswordConfirmation(userRegistrationDto.getPasswordConfirmation());
        userService.register(userRegistrationDto);

        UserEnterDto userEnterDto = new UserEnterDto();
        userEnterDto.setLogin("login1");
        userEnterDto.setPassword("password");
        Optional<User> user = userService.enter(userEnterDto);
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals("login1", user.get().getLogin());

        userEnterDto = new UserEnterDto();
        userEnterDto.setLogin("login22");
        userEnterDto.setPassword("password");
        user = userService.enter(userEnterDto);
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    public void testFinds() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setLogin("login2");
        userRegistrationDto.setPassword("password");
        userRegistrationDto.setPasswordConfirmation(userRegistrationDto.getPasswordConfirmation());
        userService.register(userRegistrationDto);

        Optional<User> user = userService.findByLogin("login2");
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals("login2", user.get().getLogin());
        long userId = user.get().getUserId();

        user = userService.findByLogin("login3");
        Assertions.assertTrue(user.isEmpty());

        user = userService.find(userId);
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals("login2", user.get().getLogin());

        user = userService.find(null);
        Assertions.assertTrue(user.isEmpty());

        user = userService.find(13375553535L);
        Assertions.assertTrue(user.isEmpty());
    }
}
