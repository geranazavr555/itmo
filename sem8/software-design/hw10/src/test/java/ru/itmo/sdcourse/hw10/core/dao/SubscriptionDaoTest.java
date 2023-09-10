package ru.itmo.sdcourse.hw10.core.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.sdcourse.hw10.core.model.Subscription;
import ru.itmo.sdcourse.hw10.core.persistance.storage.JavaSerializationStorage;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubscriptionDaoTest {
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
        var dao = new SubscriptionDao(storage);
        assertEquals(1, dao.getFreeId());
        assertEquals(2, dao.getFreeId());
        assertEquals(3, dao.getFreeId());
    }

    @Test
    public void save() {
        var dao = new SubscriptionDao(storage);
        var now = Instant.now();
        dao.save(new Subscription(dao.getFreeId(), 11, now, now.plus(Duration.ofDays(7))));
        Optional<Subscription> optSubscription = dao.find(1L);
        assertTrue(optSubscription.isPresent());
        assertEquals(new Subscription(1L, 11, now, now.plus(Duration.ofDays(7))), optSubscription.get());
    }
}
