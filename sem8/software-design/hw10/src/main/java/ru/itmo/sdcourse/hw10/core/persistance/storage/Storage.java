package ru.itmo.sdcourse.hw10.core.persistance.storage;

import ru.itmo.sdcourse.hw10.core.persistance.HasId;
import ru.itmo.sdcourse.hw10.core.persistance.event.AddEvent;
import ru.itmo.sdcourse.hw10.core.persistance.event.ChangeEvent;
import ru.itmo.sdcourse.hw10.core.persistance.event.DeleteEvent;
import ru.itmo.sdcourse.hw10.core.persistance.event.Event;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Storage {
    void append(Event event);

    List<Event> find(Predicate<Event> filter);

    <T extends Event> void registerHandler(Class<? extends T> klass, Consumer<Event> handler);

    void shutdown();

    default List<Event> findAll() {
        return find(event -> true);
    }

    default <ID> List<Event> findByClass(Class<? extends HasId<? extends ID>> klass) {
        return find(event -> {
            if (event instanceof ChangeEvent<?> changeEvent && klass.isAssignableFrom(changeEvent.klass()))
                return true;

            if (event instanceof DeleteEvent<?> deleteEvent && klass.isAssignableFrom(deleteEvent.klass()))
                return true;

            return klass.isAssignableFrom(event.getClass());
        });
    }

    default <ID> List<Event> findByInstance(Class<? extends HasId<? extends ID>> klass, ID id) {
        return findByClass(klass).stream().filter(event -> {
            @SuppressWarnings("unchecked")
            var castedEvent = (HasId<? extends ID>) event;
            return id.equals(castedEvent.id());
        }).collect(Collectors.toList());
    }

    default <T extends AddEvent> List<T> findAddEvents(Class<T> klass) {
        //noinspection unchecked
        return find(event -> klass.isAssignableFrom(event.getClass()))
                .stream()
                .map(event -> (T) event)
                .collect(Collectors.toList());
    }

    default <T extends AddEvent> List<T> findAddEvents(Class<T> klass, Predicate<T> filter) {
        return findAddEvents(klass).stream().filter(filter).collect(Collectors.toList());
    }
}
