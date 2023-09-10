package ru.itmo.sdcourse.hw10.core.model;

import java.time.Instant;

public class Entrance extends Access {
    public Entrance(long userId, long subscriptionId, Instant timestamp) {
        super(userId, subscriptionId, timestamp);
    }
}
