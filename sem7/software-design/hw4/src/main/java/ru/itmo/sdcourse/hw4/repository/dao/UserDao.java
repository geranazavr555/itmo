package ru.itmo.sdcourse.hw4.repository.dao;

import ru.itmo.sdcourse.hw4.model.User;

import java.util.Optional;

public interface UserDao {
    void saveWithPasswordHashing(User user, String password);
    Optional<User> findByLoginAndPassword(String login, String password);
}
