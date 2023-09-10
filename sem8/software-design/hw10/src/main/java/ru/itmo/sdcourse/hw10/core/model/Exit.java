package ru.itmo.sdcourse.hw10.core.model;

import java.time.Instant;

public class Exit extends Access {
    public Exit(long userId, long subscriptionId, Instant timestamp) {
        super(userId, subscriptionId, timestamp);
    }
}
