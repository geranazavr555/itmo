package ru.itmo.sdcourse.hw10.core.model;

import lombok.Builder;
import ru.itmo.sdcourse.hw10.core.persistance.event.AddEvent;
import ru.itmo.sdcourse.hw10.core.persistance.HasId;

@Builder(builderClassName = "Builder", toBuilder = true)
public record User(Long id, String userInfo) implements HasId<Long>, AddEvent {
}
