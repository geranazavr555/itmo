package ru.itmo.sdcourse.hw4.repository.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.itmo.sdcourse.hw4.repository.dao.UserDao;
import ru.itmo.sdcourse.hw4.model.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
    private static final String SALT = "80c88b1cfcefaeec36186e89b94e8a1e4a33327b";

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(DataSource dataSource) {
        super();
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
        assert jdbcTemplate != null;
    }

    @Override
    public void saveWithPasswordHashing(User user, String password) {
        jdbcTemplate.update(
                "INSERT INTO users (login, passwordHash, creationTime) VALUES (?, SHA1(CONCAT(?, ?, ?)), NOW())",
                user.getLogin(), password, user.getLogin(), SALT
        );
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        List<User> users = jdbcTemplate.query(
                "SELECT * FROM users WHERE login=? AND passwordHash=SHA1(CONCAT(?, ?, ?))",
                new BeanPropertyRowMapper<>(User.class),
                login, password, login, SALT
        );

        if (users.isEmpty())
            return Optional.empty();

        if (users.size() > 1)
            throw new RuntimeException("More than 1 user with specified credentials");

        return Optional.of(users.get(0));
    }
}
