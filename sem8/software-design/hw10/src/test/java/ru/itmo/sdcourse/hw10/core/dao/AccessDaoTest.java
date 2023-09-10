package ru.itmo.sdcourse.hw10.core.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.sdcourse.hw10.core.model.Access;
import ru.itmo.sdcourse.hw10.core.model.Entrance;
import ru.itmo.sdcourse.hw10.core.model.Exit;
import ru.itmo.sdcourse.hw10.core.persistance.storage.JavaSerializationStorage;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccessDaoTest {
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
        var dao = new AccessDao(storage);
        List<Access> accesses = dao.findBySubscriptionId(123);
        assertTrue(accesses.isEmpty());
    }

    @Test
    public void simple() {
        var dao = new AccessDao(storage);
        var now = Instant.now();
        var timestamps = new Instant[12];
        timestamps[0] = now;
        for (int i = 1; i < 12; ++i)
            timestamps[i] = timestamps[i - 1].plus(Duration.ofHours(1));

        dao.save(new Entrance(1, 11, timestamps[0]));
        dao.save(new Entrance(2, 22, timestamps[1]));
        dao.save(new Exit(2, 22, timestamps[2]));
        dao.save(new Exit(1, 11, timestamps[3]));
        dao.save(new Entrance(3, 33, timestamps[4]));
        dao.save(new Exit(3, 33, timestamps[5]));
        dao.save(new Entrance(2, 22, timestamps[6]));
        dao.save(new Entrance(1, 11, timestamps[7]));
        dao.save(new Exit(1, 11, timestamps[8]));
        dao.save(new Entrance(3, 33, timestamps[9]));
        dao.save(new Exit(2, 22, timestamps[10]));
        dao.save(new Exit(3, 33, timestamps[11]));

        var actual = dao.findBySubscriptionId(11);
        var expected = List.of(
                new Entrance(1, 11, timestamps[0]),
                new Exit(1, 11, timestamps[3]),
                new Entrance(1, 11, timestamps[7]),
                new Exit(1, 11, timestamps[8])
        );

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));

        actual = dao.findBySubscriptionId(22);
        expected = List.of(
                new Entrance(2, 22, timestamps[1]),
                new Exit(2, 22, timestamps[2]),
                new Entrance(2, 22, timestamps[6]),
                new Exit(2, 22, timestamps[10])
        );

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));

        actual = dao.findBySubscriptionId(33);
        expected = List.of(
                new Entrance(3, 33, timestamps[4]),
                new Exit(3, 33, timestamps[5]),
                new Entrance(3, 33, timestamps[9]),
                new Exit(3, 33, timestamps[11])
        );

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }
}
