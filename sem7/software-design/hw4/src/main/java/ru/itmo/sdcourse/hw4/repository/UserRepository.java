package ru.itmo.sdcourse.hw4.repository;

import org.springframework.stereotype.Repository;
import ru.itmo.sdcourse.hw4.model.User;
import ru.itmo.sdcourse.hw4.repository.dao.UserDao;

import java.util.Optional;

@Repository
public interface UserRepository extends ApplicationRepository<User>, UserDao {
    Optional<User> findByLogin(String login);
}
