package ru.itmo.sdcourse.hw10.core.persistance.storage;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itmo.sdcourse.hw10.core.persistance.event.Event;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.*;

@Component
public class JavaSerializationStorage implements Storage {
    private static final Logger logger = LogManager.getLogger(JavaSerializationStorage.class);

    private final Path path;
    private final List<Event> state = new LinkedList<>();
    private final ReadWriteLock stateLock = new ReentrantReadWriteLock();
    private volatile ObjectOutputStream objectOutputStream;
    private final Map<Class<? extends Event>, Consumer<Event>> handlers = new ConcurrentHashMap<>();

    @Autowired
    public JavaSerializationStorage(@Value("${storage.java-serialization.path}") String path) {
        this(Paths.get(path));
    }

    public JavaSerializationStorage(Path path) {
        this.path = path;
        sync();
    }

    @Override
    public void append(Event event) {
        logger.info("Write event " + event);

        Lock lock = stateLock.writeLock();
        lock.lock();
        try {
            objectOutputStream.writeObject(event);
            state.add(event);
        } catch (Exception e) {
            String message = "Failed to append event " + event + ": " + e.getMessage();
            logger.error(message);
            throw new StorageException(message, e);
        } finally {
            lock.unlock();
        }

        for (Map.Entry<Class<? extends Event>, Consumer<Event>> classAndHandler : handlers.entrySet()) {
            var klass = classAndHandler.getKey();
            var handler = classAndHandler.getValue();
            if (klass.isAssignableFrom(event.getClass())) {
                handler.accept(klass.cast(event));
            }
        }
    }

    private void sync() {
        logger.info("Syncing with persistent storage");

        Lock lock = stateLock.writeLock();
        lock.lock();

        state.clear();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path, READ, CREATE))) {
            //noinspection InfiniteLoopStatement
            while (true) {
                var event = (Event) objectInputStream.readObject();
                state.add(event);
            }
        } catch (EOFException | NoSuchFileException ignored) {
            // No operations
        } catch (Exception e) {
            String message = "Failed to sync: " + e.getMessage();
            logger.error(message);
            throw new StorageException(message, e);
        } finally {
            try {
                objectOutputStream = initObjectOutputStream(path);
                for (Event event : state) {
                    objectOutputStream.writeObject(event);
                }
            } catch (Exception e) {
                logger.fatal("Exception while syncing with the underlying device. Storage may be totally corrupted.");
                //noinspection ThrowFromFinallyBlock
                throw new StorageException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public List<Event> find(Predicate<Event> filter) {
        return copyState().stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public <T extends Event> void registerHandler(Class<? extends T> klass, Consumer<Event> handler) {
        handlers.put(klass, handler);
    }

    @SneakyThrows
    @Override
    public void shutdown() {
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    private List<Event> copyState() {
        Lock lock = stateLock.readLock();
        lock.lock();
        try {
            return List.copyOf(state);
        } finally {
            lock.unlock();
        }
    }

    private static ObjectOutputStream initObjectOutputStream(Path path) {
        try {
            Files.deleteIfExists(path);
            return new ObjectOutputStream(Files.newOutputStream(path, WRITE, CREATE, DSYNC));
        } catch (IOException e) {
            logger.error("Failed to initialize JavaSerializationStorage: " + e.getMessage());
            throw new UncheckedIOException(e);
        }
    }
}
