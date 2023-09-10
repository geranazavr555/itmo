package ru.itmo.sdcourse.hw10.core.persistance.event;

import ru.itmo.sdcourse.hw10.core.persistance.HasId;

import java.io.Serializable;

public record DeleteEvent<ID extends Serializable>(
        Class<? extends HasId<? extends ID>> klass,
        ID id
) implements Event, HasId<ID> {
}
