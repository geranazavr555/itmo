package ru.itmo.sdcourse.hw10.reports.service;

import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.sdcourse.hw10.core.dao.AccessDao;
import ru.itmo.sdcourse.hw10.core.dao.SubscriptionDao;
import ru.itmo.sdcourse.hw10.core.dao.UserDao;
import ru.itmo.sdcourse.hw10.core.model.Entrance;
import ru.itmo.sdcourse.hw10.core.model.Exit;
import ru.itmo.sdcourse.hw10.core.model.User;
import ru.itmo.sdcourse.hw10.core.persistance.event.Event;
import ru.itmo.sdcourse.hw10.core.persistance.storage.JavaSerializationStorage;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;
import ru.itmo.sdcourse.hw10.reports.model.VisitStatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class ReportsServiceTest {
    private Path path;
    private Storage storage;
    private AccessDao accessDao;
    private UserDao userDao;

    @BeforeEach
    public void preparePath() throws IOException {
        path = Files.createTempFile(getClass().getSimpleName(), ".bin");
        storage = new JavaSerializationStorage(path);
        accessDao = new AccessDao(storage);
        userDao = new UserDao(storage);
    }

    @AfterEach
    public void clearPath() throws IOException {
        Files.deleteIfExists(path);
        path = null;
        storage = null;
        accessDao = null;
        userDao = null;
    }

    @Test
    public void empty() {
        var registeredEntranceHandler = new MutableBoolean();
        var registeredExitHandler = new MutableBoolean();
        var service = new ReportsService(accessDao, userDao, new Storage() {
            @Override
            public void append(Event event) {
                storage.append(event);
            }

            @Override
            public List<Event> find(Predicate<Event> filter) {
                return storage.find(filter);
            }

            @Override
            public <T extends Event> void registerHandler(Class<? extends T> klass, Consumer<Event> handler) {
                if (Exit.class.equals(klass))
                    registeredExitHandler.setTrue();
                if (Entrance.class.equals(klass))
                    registeredEntranceHandler.setTrue();
                storage.registerHandler(klass, handler);
            }

            @Override
            public void shutdown() {

            }
        });
        var visitStat = service.getVisitStatistics();
        assertTrue(registeredExitHandler.booleanValue());
        assertTrue(registeredEntranceHandler.booleanValue());
        assertEquals(new VisitStatistics(Map.of(), Map.of()), visitStat);
    }

    @Test
    public void updates() {
        var service = new ReportsService(accessDao, userDao, storage);
        assertEquals(new VisitStatistics(Map.of(), Map.of()), service.getVisitStatistics());

        userDao.save(new User(1L, "abacaba"));
        userDao.save(new User(2L, "kek"));

        var now = Instant.now();
        accessDao.save(new Entrance(1, 1, now));
        accessDao.save(new Exit(1, 1, now.plus(Duration.ofHours(1))));
        now = Instant.now();
        accessDao.save(new Entrance(2, 2, now));
        accessDao.save(new Exit(2, 2, now.plus(Duration.ofHours(2))));

        var actual = service.getVisitStatistics();
        var expected = new VisitStatistics(
                Map.of(LocalDate.ofInstant(now, ZoneId.systemDefault()), 2),
                Map.of(
                        new User(1L, "abacaba"), Duration.ofHours(1),
                        new User(2L, "kek"), Duration.ofHours(2)
                )
        );
        assertEquals(expected, actual);
    }
}
