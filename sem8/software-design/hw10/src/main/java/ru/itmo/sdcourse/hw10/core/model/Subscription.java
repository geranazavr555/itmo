package ru.itmo.sdcourse.hw10.core.model;

import lombok.Builder;
import ru.itmo.sdcourse.hw10.core.persistance.event.AddEvent;
import ru.itmo.sdcourse.hw10.core.persistance.HasId;

import java.time.Instant;

@Builder(builderClassName = "Builder", toBuilder = true)
public record Subscription(Long id, long userId, Instant issued, Instant notAfter) implements HasId<Long>, AddEvent {
}
