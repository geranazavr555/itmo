package ru.itmo.sdcourse.hw10.core.persistance.dao;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.sdcourse.hw10.core.persistance.HasId;
import ru.itmo.sdcourse.hw10.core.persistance.event.AddEvent;
import ru.itmo.sdcourse.hw10.core.persistance.event.Event;
import ru.itmo.sdcourse.hw10.core.persistance.storage.JavaSerializationStorage;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;
import ru.itmo.sdcourse.hw10.core.persistance.storage.StorageException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractDaoTest {
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
        var dao = new FakeDao(storage);
        Optional<FakeModel> opt = dao.find(1);
        assertTrue(opt.isEmpty());
    }

    @Test
    public void save() {
        MutableInt appendCalled = new MutableInt(0);
        var dao = new FakeDao(new Storage() {
            @Override
            public void append(Event event) {
                appendCalled.increment();
                storage.append(event);
            }

            @Override
            public List<Event> find(Predicate<Event> filter) {
                return storage.find(filter);
            }

            @Override
            public <T extends Event> void registerHandler(Class<? extends T> klass, Consumer<Event> handler) {

            }

            @Override
            public void shutdown() {

            }
        });

        dao.save(new FakeModel(1, "a"));
        dao.save(new FakeModel(2, "bb"));
        dao.save(new FakeModel(3, "ccc"));

        assertEquals(3, appendCalled.intValue());
    }

    @Test
    public void roundTripWithIds() {
        var dao = new FakeDao(storage);

        dao.save(new FakeModel(dao.getFreeId(), "a"));
        dao.save(new FakeModel(dao.getFreeId(), "bb"));
        dao.save(new FakeModel(dao.getFreeId(), "ccc"));
        dao.save(new FakeModel(dao.getFreeId(), "dddd"));

        var actual = dao.find();
        var expected = Set.of(
                new FakeModel(1, "a"),
                new FakeModel(2, "bb"),
                new FakeModel(3, "ccc"),
                new FakeModel(4, "dddd")
        );

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void simpleChanges() {
        var dao = new FakeDao(storage);

        dao.save(new FakeModel(dao.getFreeId(), "a"));
        dao.save(new FakeModel(dao.getFreeId(), "bb"));
        dao.save(new FakeModel(dao.getFreeId(), "ccc"));
        dao.save(new FakeModel(dao.getFreeId(), "dddd"));

        dao.update(new FakeModel(1, "a1"));
        dao.save(new FakeModel(3, "ccc1"));
        dao.save(new FakeModel(4, "dddd1"));

        var actual = dao.find();
        var expected = Set.of(
                new FakeModel(1, "a1"),
                new FakeModel(2, "bb"),
                new FakeModel(3, "ccc1"),
                new FakeModel(4, "dddd1")
        );

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));

        dao.delete(new FakeModel(4, "dddd1"));
        assertTrue(dao.find(4).isEmpty());

        actual = dao.find();
        expected = Set.of(
                new FakeModel(1, "a1"),
                new FakeModel(2, "bb"),
                new FakeModel(3, "ccc1")
        );

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void heterogeneous() {
        var dao = new FakeDao(storage);
        var dao2 = new FakeDao2(storage);

        dao.save(new FakeModel(dao.getFreeId(), "a"));
        dao2.save(new FakeModel2(dao2.getFreeId(), 5));
        dao.save(new FakeModel(dao.getFreeId(), "ccc"));
        dao2.save(new FakeModel2(dao2.getFreeId(), 9));

        dao.update(new FakeModel(1, "a1"));
        dao2.save(new FakeModel2(2L, 8));

        var actual = dao.find();
        var expected = Set.of(
                new FakeModel(1, "a1"),
                new FakeModel(2, "ccc")
        );

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));

        var actual2 = dao2.find();
        var expected2 = Set.of(
                new FakeModel2(1L, 5),
                new FakeModel2(2L, 8)
        );

        assertTrue(actual2.containsAll(expected2));
        assertTrue(expected2.containsAll(actual2));

        dao.delete(new FakeModel(1, "a1"));
        dao2.delete(new FakeModel2(2L, 9));


        var actual3 = dao.find();
        var expected3 = Set.of(new FakeModel(2, "ccc"));

        assertTrue(actual3.containsAll(expected3));
        assertTrue(expected3.containsAll(actual3));

        var actual4 = dao2.find();
        var expected4 = Set.of(new FakeModel2(1L, 5));

        assertTrue(actual4.containsAll(expected4));
        assertTrue(expected4.containsAll(actual4));
    }

    @Test
    public void illegalUpdate() {
        var dao = new FakeDao(storage);
        assertThrows(StorageException.class, () -> dao.update(new FakeModel(1, "ddd")));
    }

    private static class FakeDao extends AbstractDao<Integer, FakeModel> {
        private int id = 1;

        protected FakeDao(Storage storage) {
            super(storage, FakeModel.class);
        }

        @Override
        protected Integer getFreeId() {
            return id++;
        }
    }

    private static class FakeDao2 extends AbstractDao<Long, FakeModel2> {
        private long id = 1;

        protected FakeDao2(Storage storage) {
            super(storage, FakeModel2.class);
        }

        @Override
        protected Long getFreeId() {
            return id++;
        }
    }

    public record FakeModel(Integer id, String data) implements HasId<Integer>, AddEvent {
    }

    public record FakeModel2(Long id, Integer data) implements HasId<Long>, AddEvent {
    }
}
