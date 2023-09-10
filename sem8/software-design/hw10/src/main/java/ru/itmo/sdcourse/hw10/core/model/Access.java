package ru.itmo.sdcourse.hw10.core.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.itmo.sdcourse.hw10.core.persistance.event.AddEvent;

import java.time.Instant;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public abstract class Access implements AddEvent {
    protected final long userId;
    protected final long subscriptionId;
    protected final Instant timestamp;
}
