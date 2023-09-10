package ru.itmo.sdcourse.hw10.entrance.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.sdcourse.hw10.FakeClock;
import ru.itmo.sdcourse.hw10.core.dao.AccessDao;
import ru.itmo.sdcourse.hw10.core.dao.SubscriptionDao;
import ru.itmo.sdcourse.hw10.core.model.Subscription;
import ru.itmo.sdcourse.hw10.core.persistance.storage.JavaSerializationStorage;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class EntranceServiceTest {
    private Path path;
    private Storage storage;
    private SubscriptionDao subscriptionDao;
    private AccessDao accessDao;

    @BeforeEach
    public void preparePath() throws IOException {
        path = Files.createTempFile(getClass().getSimpleName(), ".bin");
        storage = new JavaSerializationStorage(path);
        subscriptionDao = new SubscriptionDao(storage);
        accessDao = new AccessDao(storage);
    }

    @AfterEach
    public void clearPath() throws IOException {
        Files.deleteIfExists(path);
        path = null;
        storage = null;
        subscriptionDao = null;
        accessDao = null;
    }

    @Test
    public void empty() {
        var now = Instant.now();
        var clock = new FakeClock(now);
        var service = new EntranceService(subscriptionDao, accessDao, clock);
        subscriptionDao.save(new Subscription(11L, 1, now, now.plus(Duration.ofDays(1))));
        assertFalse(service.exit(11));
    }

    @Test
    public void complex() {
        var now = Instant.now();
        var clock = new FakeClock(now);
        var service = new EntranceService(subscriptionDao, accessDao, clock);

        subscriptionDao.save(new Subscription(11L, 1, now, now.plus(Duration.ofDays(1))));
        subscriptionDao.save(new Subscription(22L, 1, now, now.plus(Duration.ofDays(2))));
        subscriptionDao.save(new Subscription(33L, 2, now, now.plus(Duration.ofDays(3))));

        assertTrue(service.enter(11));
        clock.add(Duration.ofHours(1));
        assertTrue(service.exit(11));
        clock.add(Duration.ofMinutes(15));
        assertTrue(service.enter(22));
        clock.add(Duration.ofHours(2));
        assertTrue(service.exit(22));

        clock.add(Duration.ofDays(1));

        assertFalse(service.enter(11));
        assertTrue(service.enter(22));
        assertFalse(service.enter(22));

        clock.add(Duration.ofDays(1));
        assertTrue(service.exit(22));
        assertFalse(service.exit(22));
        assertFalse(service.enter(11));
        assertFalse(service.exit(11));

        assertFalse(service.enter(44));
        assertFalse(service.enter(44));
        assertFalse(service.exit(44));
        assertFalse(service.exit(44));
    }
}
