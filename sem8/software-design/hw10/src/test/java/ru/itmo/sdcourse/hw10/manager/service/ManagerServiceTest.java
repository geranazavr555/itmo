package ru.itmo.sdcourse.hw10.manager.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.sdcourse.hw10.FakeClock;
import ru.itmo.sdcourse.hw10.core.dao.AccessDao;
import ru.itmo.sdcourse.hw10.core.dao.SubscriptionDao;
import ru.itmo.sdcourse.hw10.core.dao.UserDao;
import ru.itmo.sdcourse.hw10.core.model.Subscription;
import ru.itmo.sdcourse.hw10.core.model.User;
import ru.itmo.sdcourse.hw10.core.persistance.storage.JavaSerializationStorage;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;
import ru.itmo.sdcourse.hw10.manager.model.UserSubscription;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerServiceTest {
    private Path path;
    private Storage storage;
    private SubscriptionDao subscriptionDao;
    private UserDao userDao;

    @BeforeEach
    public void preparePath() throws IOException {
        path = Files.createTempFile(getClass().getSimpleName(), ".bin");
        storage = new JavaSerializationStorage(path);
        subscriptionDao = new SubscriptionDao(storage);
        userDao = new UserDao(storage);
    }

    @AfterEach
    public void clearPath() throws IOException {
        Files.deleteIfExists(path);
        path = null;
        storage = null;
        subscriptionDao = null;
        userDao = null;
    }

    @Test
    public void empty() {
        var now = Instant.now();
        var clock = new FakeClock(now);
        var service = new ManagerService(subscriptionDao, userDao, clock);

        assertTrue(service.findSubscription(11).isEmpty());
    }

    @Test
    public void register() {
        var now = Instant.now();
        var clock = new FakeClock(now);
        var service = new ManagerService(subscriptionDao, userDao, clock);

        User user = service.registerUser("abacaba");
        assertEquals(new User(1L, "abacaba"), user);

        user = service.registerUser("kek");
        assertEquals(new User(2L, "kek"), user);

        user = service.registerUser("abacaba");
        assertEquals(new User(3L, "abacaba"), user);
    }

    @Test
    public void issueSubscription() {
        var now = Instant.now();
        var clock = new FakeClock(now);
        var service = new ManagerService(subscriptionDao, userDao, clock);

        User user = service.registerUser("abacaba");
        assertEquals(new User(1L, "abacaba"), user);

        Subscription subscription = service.issueSubscription(user, Duration.ofDays(30));
        assertEquals(new Subscription(1L, 1, now, now.plus(Duration.ofDays(30))), subscription);

        var userSubscription = service.findSubscription(1L);
        assertTrue(userSubscription.isPresent());
        assertEquals(new UserSubscription(user, subscription), userSubscription.get());
    }

    @Test
    public void illegalState() {
        var now = Instant.now();
        var clock = new FakeClock(now);
        var service = new ManagerService(subscriptionDao, userDao, clock);
        storage.append(new Subscription(1L, 1, now, now.plus(Duration.ofDays(30))));
        assertThrows(RuntimeException.class, () -> service.findSubscription(1L));
    }

    @Test
    public void prolongSubscription() {
        var now = Instant.now();
        var clock = new FakeClock(now);
        var service = new ManagerService(subscriptionDao, userDao, clock);

        User user = service.registerUser("abacaba");
        assertEquals(new User(1L, "abacaba"), user);

        Subscription subscription = service.issueSubscription(user, Duration.ofDays(30));
        assertEquals(new Subscription(1L, 1, now, now.plus(Duration.ofDays(30))), subscription);

        var userSubscription = service.findSubscription(1L);
        assertTrue(userSubscription.isPresent());
        assertEquals(new UserSubscription(user, subscription), userSubscription.get());

        clock.add(Duration.ofDays(30));

        Subscription prolonged = service.prolongSubscription(subscription, Duration.ofDays(30));
        assertEquals(new Subscription(1L, 1, now, now.plus(Duration.ofDays(60))), prolonged);
    }
}
