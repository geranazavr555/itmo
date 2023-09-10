package ru.itmo.sdcourse.hw10.core.persistance.event;

import ru.itmo.sdcourse.hw10.core.persistance.HasId;

import java.io.Serializable;
import java.util.Map;

public record ChangeEvent<ID extends Serializable>(
        Class<? extends HasId<? extends ID>> klass,
        ID id,
        Map<String, Object> fieldNameToNewValue
) implements Event, HasId<ID> {
}
