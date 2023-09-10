package ru.itmo.sdcourse.hw10.core.persistance.dao;

import lombok.SneakyThrows;
import org.apache.commons.lang3.Validate;
import ru.itmo.sdcourse.hw10.core.persistance.HasId;
import ru.itmo.sdcourse.hw10.core.persistance.event.AddEvent;
import ru.itmo.sdcourse.hw10.core.persistance.event.ChangeEvent;
import ru.itmo.sdcourse.hw10.core.persistance.event.DeleteEvent;
import ru.itmo.sdcourse.hw10.core.persistance.event.Event;
import ru.itmo.sdcourse.hw10.core.persistance.storage.Storage;
import ru.itmo.sdcourse.hw10.core.persistance.storage.StorageException;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.RecordComponent;
import java.util.*;

public abstract class AbstractDao<ID extends Serializable, T extends Record & HasId<? extends ID> & Serializable> {
    private final Storage storage;
    private final Class<? extends T> klass;
    private final RecordComponent[] recordComponents;
    private final Constructor<T> constructor;

    protected AbstractDao(Storage storage, Class<? extends T> klass) {
        Validate.isTrue(klass.isRecord());
        this.storage = storage;
        this.klass = klass;
        this.recordComponents = klass.getRecordComponents();
        //noinspection unchecked
        this.constructor = (Constructor<T>) klass.getDeclaredConstructors()[0];
    }

    public void save(T obj) {
        find(obj.id()).map(oldObj -> updateImpl(oldObj, obj)).orElseGet(() -> saveImpl(obj));
    }

    private T saveImpl(T obj) {
        storage.append((AddEvent) obj);
        return obj;
    }

    public void update(T obj) {
        ID id = obj.id();
        Optional<T> optObj = find(id);
        if (optObj.isEmpty())
            throw new StorageException("No such object: " + obj);
        updateImpl(optObj.get(), obj);
    }

    @SneakyThrows
    private T updateImpl(T oldObj, T newObj) {
        Map<String, Object> changeMap = new HashMap<>();
        for (var recordComponent : recordComponents) {
            var accessor = recordComponent.getAccessor();
            var newValue = accessor.invoke(newObj);
            if (!accessor.invoke(oldObj).equals(newValue))
                changeMap.put(recordComponent.getName(), newValue);
        }
        storage.append(new ChangeEvent<>(klass, newObj.id(), changeMap));
        return newObj;
    }

    public void delete(T obj) {
        storage.append(new DeleteEvent<>(klass, obj.id()));
    }

    public Optional<T> find(ID id) {
        var events = storage.findByInstance(klass, id);
        T obj = null;
        for (Event event : events) {
            if (event instanceof AddEvent addEvent) {
                obj = replayAddEvent(addEvent);
            } else if (event instanceof ChangeEvent<? extends Serializable> changeEvent) {
                obj = replayChangeEvent(changeEvent, obj);
            } else if (event instanceof DeleteEvent<? extends Serializable>) {
                obj = null;
            }
        }
        return Optional.ofNullable(obj);
    }

    public Collection<T> find() {
        Map<ID, T> map = new HashMap<>();
        var events = storage.findByClass(klass);
        for (Event event : events) {
            if (event instanceof AddEvent addEvent) {
                T obj = replayAddEvent(addEvent);
                map.put(obj.id(), obj);
            } else if (event instanceof ChangeEvent<? extends Serializable> changeEvent) {
                @SuppressWarnings("SuspiciousMethodCalls")
                T obj = map.get(changeEvent.id());
                if (obj == null)
                    throw new StorageException("Inconsistent data in storage");
                obj = replayChangeEvent(changeEvent, obj);
                map.put(obj.id(), obj);
            } else if (event instanceof DeleteEvent<? extends Serializable> deleteEvent) {
                //noinspection SuspiciousMethodCalls
                map.remove(deleteEvent.id());
            }
        }
        return map.values();
    }

    private T replayAddEvent(AddEvent addEvent) {
        return klass.cast(addEvent);
    }

    @SneakyThrows
    private <LID extends Serializable> T replayChangeEvent(ChangeEvent<LID> changeEvent, T obj) {
        Object[] constructorArgs = new Object[recordComponents.length];
        var map = changeEvent.fieldNameToNewValue();
        for (int i = 0; i < recordComponents.length; ++i) {
            var fieldValue = map.get(recordComponents[i].getName());
            if (fieldValue != null) {
                constructorArgs[i] = fieldValue;
            } else {
                constructorArgs[i] = recordComponents[i].getAccessor().invoke(obj);
            }
        }

        return constructor.newInstance(constructorArgs);
    }

    protected abstract ID getFreeId();
}
