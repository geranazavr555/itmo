package ru.itmo.sdcourse.hw10.core.persistance.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.sdcourse.hw10.core.persistance.event.AddEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JavaSerializationStorageTest {
    private Path path;

    @BeforeEach
    public void preparePath() throws IOException {
        path = Files.createTempFile(getClass().getSimpleName(), ".bin");
    }

    @AfterEach
    public void clearPath() throws IOException {
        Files.deleteIfExists(path);
        path = null;
    }

    @Test
    public void emptySync() {
        var storage = new JavaSerializationStorage(path);
        var events = storage.findAll();
        assertTrue(events.isEmpty());
        assertTrue(storage.findAll().isEmpty());
        assertTrue(storage.findAddEvents(AddEvent.class).isEmpty());
        assertTrue(storage.findAddEvents(AddEvent.class, x -> true).isEmpty());
    }

    @Test
    public void roundTripSingle() {
        var storage = new JavaSerializationStorage(path);
        storage.append(new FakeAddEvent(1, "abacaba"));

        var events = storage.findAll();
        assertEquals(List.of(
                new FakeAddEvent(1, "abacaba")
        ), events);
    }

    @Test
    public void roundTrip() {
        var storage = new JavaSerializationStorage(path);
        storage.append(new FakeAddEvent(1, "abacaba"));
        storage.append(new FakeAddEvent(2, "sdfsdf"));
        storage.append(new FakeAddEvent(3, "fdswer"));

        var events = storage.findAll();
        assertEquals(List.of(
                new FakeAddEvent(1, "abacaba"),
                new FakeAddEvent(2, "sdfsdf"),
                new FakeAddEvent(3, "fdswer")
        ), events);
    }

    @Test
    public void multipleStarts() {
        var storage1 = new JavaSerializationStorage(path);
        storage1.append(new FakeAddEvent(1, "abacaba"));
        storage1.append(new FakeAddEvent(2, "sdfsdf"));
        storage1.append(new FakeAddEvent(3, "fdswer"));

        var events = storage1.findAll();
        assertEquals(List.of(
                new FakeAddEvent(1, "abacaba"),
                new FakeAddEvent(2, "sdfsdf"),
                new FakeAddEvent(3, "fdswer")
        ), events);

        storage1.shutdown();

        var storage2 = new JavaSerializationStorage(path);

        events = storage2.findAll();
        assertEquals(List.of(
                new FakeAddEvent(1, "abacaba"),
                new FakeAddEvent(2, "sdfsdf"),
                new FakeAddEvent(3, "fdswer")
        ), events);

        storage2.append(new FakeAddEvent(4, "qqqq"));
        storage2.shutdown();

        var storage3 = new JavaSerializationStorage(path);;
        events = storage3.findAll();
        assertEquals(List.of(
                new FakeAddEvent(1, "abacaba"),
                new FakeAddEvent(2, "sdfsdf"),
                new FakeAddEvent(3, "fdswer"),
                new FakeAddEvent(4, "qqqq")
        ), events);
    }

    private record FakeAddEvent(Integer a, String b) implements AddEvent {
    }
}
