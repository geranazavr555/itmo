package ru.itmo.sdcourse.hw10.core.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.sdcourse.hw10.core.model.User;
import ru.itmo.sdcourse.hw10.core.persistance.storage.JavaSerializationStorage;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    private Path path;
    private Storage storage;

    @BeforeEach
    public void preparePath() throws IOException {
        path = Files.createTempFile(getClass().getSimpleName(), ".bin");
        storage = new JavaSerializationStorage(path);
    }

    @AfterEach
    public void clearPath() throws IOException {
        Files.deleteIfExists(path);
        path = null;
        storage = null;
    }

    @Test
    public void empty() {
        var dao = new UserDao(storage);
        assertEquals(1, dao.getFreeId());
        assertEquals(2, dao.getFreeId());
        assertEquals(3, dao.getFreeId());
    }

    @Test
    public void save() {
        var dao = new UserDao(storage);
        dao.save(new User(dao.getFreeId(), "abacaba"));
        Optional<User> optUser = dao.find(1L);
        assertTrue(optUser.isPresent());
        assertEquals(new User(1L, "abacaba"), optUser.get());
    }

    @Test
    public void multipleSave() {
        var dao = new UserDao(storage);
        dao.save(new User(dao.getFreeId(), "abacaba"));
        Optional<User> optUser = dao.find(1L);
        assertTrue(optUser.isPresent());
        assertEquals(new User(1L, "abacaba"), optUser.get());
        dao.save(new User(dao.getFreeId(), "kek"));
        optUser = dao.find(2L);
        assertTrue(optUser.isPresent());
        assertEquals(new User(2L, "kek"), optUser.get());
    }
}
